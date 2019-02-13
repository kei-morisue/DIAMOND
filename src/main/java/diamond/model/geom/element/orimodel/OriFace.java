/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
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
                double x = getXPosition(scale, centerP, sv);
                double y = getYPosition(scale, centerP, sv);
                outline.moveTo(x, y);
            } else {
            }
            outline.lineTo(
                    getXPosition(scale, centerP, sv),
                    getYPosition(scale, centerP, sv));
        }
        outline.closePath();
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
            OriVertex sv) {
        return sv.x * scale + centerP.x * (1.0 - scale);
    }

    private double getYPosition(
            double scale,
            Vector2d centerP,
            OriVertex sv) {
        return sv.y * scale + centerP.y * (1.0 - scale);
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

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

}
