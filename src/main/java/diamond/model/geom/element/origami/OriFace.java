/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.geom.util.Point2DUtil;
import diamond.view.screen.draw.style.ColorStyle;

/**
 * @author long_
 *
 */
public class OriFace {
    private ArrayList<OriHalfEdge> halfEdges = new ArrayList<>();
    private HashSet<OriHalfEdge> auxLines = new HashSet<OriHalfEdge>();
    private HashSet<OriHalfEdge> unettledLines = new HashSet<OriHalfEdge>();

    private boolean faceFront = true;

    private GeneralPath outline = null;
    private GeneralPath foldedOutline = null;
    private AffineTransform transform = null;
    public boolean footPrint = false;

    public void initialize() {
        faceFront = true;
        foldedOutline = null;
        transform = null;
    }

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

    public void setOutline(double scale) {
        OriVertex centerP = OriModelUtil.getCenterPoint(this);
        for (OriHalfEdge he : halfEdges) {
            OriVertex sv = he.getSv();
            OriVertex scaledPoint = OriModelUtil.getScaledPoint(scale, centerP,
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

    public void setFoldedOutline(GeneralPath foldedOutline) {
        this.foldedOutline = foldedOutline;
    }

    public void setFoldedOutline() {
        for (OriHalfEdge he : halfEdges) {
            OriVertex sv = he.getSv();
            Point2D p = Point2DUtil.plus(
                    sv.getFoldedPosition(),
                    sv.getOffset());
            double x = p.getX();
            double y = p.getY();
            if (foldedOutline == null) {
                foldedOutline = new GeneralPath();
                foldedOutline.moveTo(x, y);
            } else {
                foldedOutline.lineTo(x, y);
            }
        }
        foldedOutline.closePath();
    }

    public void fold(AffineTransform transform) {
        this.transform = transform;
        for (OriHalfEdge he : halfEdges) {
            he.fold(transform);
        }
        for (OriHalfEdge he : auxLines) {
            he.fold(transform);
        }
        for (OriHalfEdge he : unettledLines) {
            he.fold(transform);
        }
        this.setFoldedOutline();
    }

    public Color getColor() {
        return (isFaceFront()) ? ColorStyle.ORI_FACE_FRONT
                : ColorStyle.ORI_FACE_BACK;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
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

    public HashSet<OriHalfEdge> getUnettledLines() {
        return unettledLines;
    }

    public void setUnettledLines(HashSet<OriHalfEdge> unettledLines) {
        this.unettledLines = unettledLines;
    }

}
