/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.fold.FoldPolicy;
import diamond.view.paint.screen.draw.ColorStyle;

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
        Vector2d centerP = OriModelUtil.getCenterPoint(halfEdges);
        for (OriHalfEdge he : halfEdges) {
            OriVertex sv = he.getSv();
            Point2D scaledPoint = OriModelUtil.getScaledPoint(scale, centerP,
                    sv);
            if (outline == null) {
                outline = new GeneralPath();
                outline.moveTo(scaledPoint.getX(), scaledPoint.getY());
            } else {
            }
            outline.lineTo(scaledPoint.getX(), scaledPoint.getY());
        }
        outline.closePath();
    }

    public void setFoldedOutline() {
        for (OriHalfEdge he : halfEdges) {
            Point2D p = he.getFoldedSv();
            if (foldedOutline == null) {
                foldedOutline = new GeneralPath();
                foldedOutline.moveTo(p.getX(), p.getY());
            } else {
                foldedOutline.lineTo(p.getX(), p.getY());
            }
        }
        foldedOutline.closePath();
    }

    public void fold(AffineTransform transform, FoldPolicy foldPolicy) {
        this.transform = transform;
        for (OriHalfEdge he : halfEdges) {
            he.fold(transform);
        }
        for (OriHalfEdge he : auxLines) {
            he.foldAsAuxLine(transform, foldPolicy.getAuxClipScale());
        }
        this.setFoldedOutline();
    }

    public Color getColor() {
        return (isFaceFront()) ? ColorStyle.ORIFACE_FRONT
                : ColorStyle.ORIFACE_BACK;
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

    public GeneralPath getFoldedOutline() {
        return this.foldedOutline;
    }

}
