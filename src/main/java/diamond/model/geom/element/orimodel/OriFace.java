/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriFace {
    private ArrayList<OriHalfEdge> halfEdges = new ArrayList<>();
    private HashSet<OriHalfEdge> auxLines = new HashSet<OriHalfEdge>();

    private boolean faceFront = true;

    private GeneralPath outline = null;
    private GeneralPath foldedOutline = null;
    private AffineTransform transform = null;

    public void addHalfEdge(OriHalfEdge he) {
        halfEdges.add(he);
    }

    public void makeHalfedgeLoop() {
        int heNum = halfEdges.size();
        for (int i = 0; i < heNum; i++) {
            OriHalfEdge pre_he = halfEdges.get((i - 1 + heNum) % heNum);
            OriHalfEdge he = halfEdges.get(i);
            OriHalfEdge nxt_he = halfEdges.get((i + 1) % heNum);
            he.setNext(nxt_he);
            he.setPrev(pre_he);
        }
    }

    public void addAuxLine(OriHalfEdge he) {
        auxLines.add(he);
    }

    public void setOutline(double scale) {
        Vector2d centerP = getCenterPoint();
        for (OriHalfEdge he : halfEdges) {
            OriVertex sv = he.getSv();
            if (outline == null) {
                outline = new GeneralPath();
                outline.moveTo(
                        getXPosition(scale, centerP, sv),
                        getYPosition(scale, centerP, sv));
            } else {
            }
            outline.lineTo(
                    getXPosition(scale, centerP, sv),
                    getYPosition(scale, centerP, sv));
        }
        outline.closePath();
    }

    public void setFoldedOutline(AffineTransform transform) {
        for (OriHalfEdge he : halfEdges) {
            Vector2d v = he.getSv();
            Point2D p = new Point2D.Double();
            Point2D ptSrc = new Point2D.Double(v.x, v.y);
            transform.transform(ptSrc, p);
            if (foldedOutline == null) {
                foldedOutline = new GeneralPath();
                foldedOutline.moveTo(p.getX(), p.getY());
            } else {
                foldedOutline.lineTo(p.getX(), p.getY());
            }
        }
    }

    private Vector2d getCenterPoint() {
        Vector2d centerP = new Vector2d();
        for (OriHalfEdge he : halfEdges) {
            centerP.add(he.getSv());
        }
        centerP.scale(1.0 / halfEdges.size());
        return centerP;
    }

    private double getXPosition(
            double scale,
            Vector2d centerP,
            OriVertex v) {
        return v.x * scale + centerP.x * (1.0 - scale);
    }

    private double getYPosition(
            double scale,
            Vector2d centerP,
            OriVertex sv) {
        return sv.y * scale + centerP.y * (1.0 - scale);
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
        this.setFoldedOutline(transform);
        for (OriHalfEdge he : halfEdges) {
            Point2D ptSrc0 = new Point2D.Double(he.getSv().x,
                    he.getSv().y);
            Point2D ptSrc1 = new Point2D.Double(he.getEv().x,
                    he.getEv().y);
            transform.transform(ptSrc0, he.getFoldedSv());
            transform.transform(ptSrc1, he.getFoldedEv());
        }
        for (OriHalfEdge he : auxLines) {
            Vector2d centerP = new Vector2d();
            OriVertex sv = he.getSv();
            centerP.add(sv);
            OriVertex ev = he.getEv();
            centerP.add(ev);
            centerP.scale(0.5);
            double p0 = (sv.onCut()) ? 0.9 : 1.0;
            Point2D ptSrc0 = new Point2D.Double(
                    getXPosition(p0, centerP, sv),
                    getYPosition(p0, centerP, sv));

            double p1 = (ev.onCut()) ? 0.9 : 1.0;
            Point2D ptSrc1 = new Point2D.Double(
                    getXPosition(p1, centerP, ev),
                    getYPosition(p1, centerP, ev));
            transform.transform(ptSrc0, he.getFoldedSv());
            transform.transform(ptSrc1, he.getFoldedEv());
        }
    }

    public boolean isFaceFront() {
        return this.faceFront;
    }

    public void setFaceFront(boolean faceFront) {
        this.faceFront = faceFront;
    }

    public ArrayList<OriHalfEdge> getHalfEdges() {
        return this.halfEdges;
    }

    public HashSet<OriHalfEdge> getAuxLines() {
        return this.auxLines;
    }

    public GeneralPath getOutline() {
        return this.outline;
    }

    public AffineTransform getTransform() {
        return this.transform;
    }

}
