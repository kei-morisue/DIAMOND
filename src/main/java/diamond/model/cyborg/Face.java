/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import diamond.controller.HalfEdgeSortor;

/**
 * @author Kei Morisue
 *
 */
public class Face implements Cyborg {
    private ArrayList<HalfEdge> halfEdges = new ArrayList<>();
    private HashSet<HalfEdge> unsettledLines = new HashSet<>();

    private boolean faceFront = false;
    private AffineTransform transform = null;
    private FaceProperty property = new FaceProperty();

    public Face() {
    }

    public void initialize() {
        faceFront = false;
        transform = null;
    }

    public void add(HalfEdge he) {
        if (EdgeType.isSettled(he.getType())) {
            halfEdges.add(he);
            he.setFace(this);
        } else {
            unsettledLines.add(he);
            unsettledLines.add(he.getPair());
            he.setFace(this);
            he.getPair().setFace(this);
        }
    }

    public void add(Collection<HalfEdge> halfEdges) {
        for (HalfEdge he : halfEdges) {
            add(he);
        }
    }

    public void remove(HalfEdge he) {
        if (EdgeType.isSettled(he.getType())) {
            halfEdges.remove(he);
        } else {
            unsettledLines.remove(he);
            unsettledLines.remove(he.getPair());
        }
    }

    public HashSet<HalfEdge> getUnsettledLines() {
        return this.unsettledLines;
    }

    @Deprecated
    public void setUnsettledLines(HashSet<HalfEdge> unsettledLines) {
        this.unsettledLines = unsettledLines;
    }

    public ArrayList<HalfEdge> getHalfEdges() {
        if (halfEdges.isEmpty()) {
            return this.halfEdges;
        }
        halfEdges.sort(new HalfEdgeSortor(halfEdges.get(0)));
        return this.halfEdges;
    }

    @Deprecated
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
