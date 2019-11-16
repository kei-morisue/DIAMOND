/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Kei Morisue
 *
 */
public class Face {
    private ArrayList<HalfEdge> halfEdges = new ArrayList<>();
    private HashSet<HalfEdge> creaseLines = new HashSet<>();
    private HashSet<HalfEdge> unsettledLines = new HashSet<>();

    private boolean faceFront = true;
    private GeneralPath outline = null;
    private AffineTransform transform = null;

    public void open(HalfEdge he1) {
        halfEdges.add(he1);
    }

    public void add(HalfEdge he1) {
        HalfEdge he0 = halfEdges.get(halfEdges.size() - 1);
        he0.setNext(he1);
        halfEdges.add(he1);
        he1.setPrev(he0);
    }

    public void close(HalfEdge he1) {
        add(he1);
        HalfEdge he0 = halfEdges.get(0);
        he1.setNext(he0);
        he0.setPrev(he1);
        for (HalfEdge he : halfEdges) {
            he.setFace(this);
        }
        buildOutline(1.0);
    }

    private void buildOutline(double scale) {
        Point2D.Double centerP = CyborgUtil.getCenterPoint(this);
        for (HalfEdge he : halfEdges) {
            Vertex sv = he.getV0();
            Point2D.Double scaledPoint = Point2DUtil.scale(centerP, sv, scale);
            if (outline == null) {
                outline = new GeneralPath();
                outline.moveTo(scaledPoint.getX(), scaledPoint.getY());
            } else {
            }
            outline.lineTo(scaledPoint.getX(), scaledPoint.getY());
        }
        outline.closePath();
    }

    public HashSet<HalfEdge> getCreaseLines() {
        return this.creaseLines;
    }

    public void setCreaseLines(HashSet<HalfEdge> creaseLines) {
        this.creaseLines = creaseLines;
    }

    public HashSet<HalfEdge> getUnsettledLines() {
        return this.unsettledLines;
    }

    public void setUnsettledLines(HashSet<HalfEdge> unsettledLines) {
        this.unsettledLines = unsettledLines;
    }

    public GeneralPath getOutline() {
        return this.outline;
    }

    public void setOutline(GeneralPath outline) {
        this.outline = outline;
    }

    public ArrayList<HalfEdge> getHalfEdges() {
        return this.halfEdges;
    }

    public void setHalfEdges(ArrayList<HalfEdge> halfEdges) {
        this.halfEdges = halfEdges;
    }
}
