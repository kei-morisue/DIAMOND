/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.geom.util.OriFaceUtil;
import diamond.model.geom.util.OriVertexUtil;

/**
 * @author long_
 *
 */
public class OriFace {
    private ArrayList<OriHalfEdge> halfEdges = new ArrayList<>();
    private HashSet<OriHalfEdge> creaseLines = new HashSet<OriHalfEdge>();
    private HashSet<OriHalfEdge> unsettledLines = new HashSet<OriHalfEdge>();

    private boolean faceFront = true;

    private GeneralPath outline = null;
    private AffineTransform transform = null;
    public boolean footPrint = false;
    public boolean isPointed = false;

    public OriFace() {

    }

    public void initialize() {
        faceFront = true;
        transform = null;
        footPrint = false;
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

    public void buildOutline(double scale) {
        OriVertex centerP = OriFaceUtil.getCenterPoint(this);
        for (OriHalfEdge he : halfEdges) {
            OriVertex sv = he.getSv();
            OriVertex scaledPoint = OriVertexUtil.getScaledPoint(scale, centerP,
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

    public void fold(AffineTransform transform) {
        this.transform = transform;
        for (OriHalfEdge he : halfEdges) {
            he.fold(transform);
        }
        for (OriHalfEdge he : creaseLines) {
            he.fold(transform);
        }
        for (OriHalfEdge he : unsettledLines) {
            he.fold(transform);
        }
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

    public HashSet<OriHalfEdge> getCreaseLines() {
        return this.creaseLines;
    }

    public GeneralPath getOutline() {
        return this.outline;
    }

    public AffineTransform getTransform() {
        return this.transform;
    }

    public HashSet<OriHalfEdge> getUnsettledLines() {
        return unsettledLines;
    }

    public void setUnsettledLines(HashSet<OriHalfEdge> unsettledLines) {
        this.unsettledLines = unsettledLines;
    }

    @Deprecated
    public void setHalfEdges(ArrayList<OriHalfEdge> halfEdges) {
        this.halfEdges = halfEdges;
    }

    @Deprecated
    public void setAuxLines(HashSet<OriHalfEdge> auxLines) {
        this.creaseLines = auxLines;
    }

    @Deprecated
    public void setOutline(GeneralPath outline) {
        this.outline = outline;
    }

    @Deprecated
    public boolean isFootPrint() {
        return this.footPrint;
    }

    @Deprecated
    public void setFootPrint(boolean footPrint) {
        this.footPrint = footPrint;
    }

}
