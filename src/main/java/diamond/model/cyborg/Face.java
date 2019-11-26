/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Kei Morisue
 *
 */
public class Face implements Cyborg {
    private ArrayList<HalfEdge> halfEdges = new ArrayList<>();
    private HashSet<HalfEdge> creaseLines = new HashSet<>();
    private HashSet<HalfEdge> unsettledLines = new HashSet<>();

    private boolean faceFront = true;
    private AffineTransform transform = null;
    private FaceProperty property = new FaceProperty();

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

    public ArrayList<HalfEdge> getHalfEdges() {
        return this.halfEdges;
    }

    public void setHalfEdges(ArrayList<HalfEdge> halfEdges) {
        this.halfEdges = halfEdges;
    }

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    public boolean isFaceFront() {
        return faceFront;
    }

    public void setFaceFront(boolean faceFront) {
        this.faceFront = faceFront;
    }

    public FaceProperty getProperty() {
        return property;
    }

    public void setProperty(FaceProperty property) {
        this.property = property;
    }
}
