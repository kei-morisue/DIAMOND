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
    private HashSet<HalfEdge> unsettledLines = new HashSet<>();

    private boolean faceFront = true;
    private AffineTransform transform = null;
    private FaceProperty property = new FaceProperty();

    public Face() {
    }

    public void add(HalfEdge halfEdge) {
        halfEdges.add(halfEdge);
        halfEdge.setFace(this);
    }

    public void addUnsettled(HalfEdge he) {
        unsettledLines.add(he);
        unsettledLines.add(he.getPair());
        he.setFace(this);
        he.getPair().setFace(this);
    }

    public void removeUnsettled(HalfEdge he) {
        unsettledLines.remove(he);
        unsettledLines.remove(he.getPair());
    }

    @Deprecated
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
