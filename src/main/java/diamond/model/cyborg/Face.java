/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import diamond.controller.HalfEdgeSortor;

/**
 * @author Kei Morisue
 *
 */
public class Face implements Cyborg, Serializable {
    private ArrayList<HalfEdge> halfEdges = new ArrayList<>();
    private HashSet<HalfEdge> unsettledLines = new HashSet<>();

    private boolean faceFront = false;
    private AffineTransform transform = null;
    private FaceProperty property = new FaceProperty();

    public Face() {
    }

    public Face(Face face) {
        property.setDisabled(face.getProperty().isDisabled());
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

    @Override
    public Double clip(AffineTransform transform) {
        Rectangle2D.Double r = halfEdges.get(0).clip(transform);
        for (HalfEdge he : halfEdges) {
            r.add(he.clip(transform));
        }
        return r;
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

    @Deprecated
    public ArrayList<HalfEdge> getHalfEdges() {
        return halfEdges;
    }

    public ArrayList<HalfEdge> getSortedEdges() {
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

    @Deprecated
    public void setProperty(FaceProperty property) {
        this.property = property;
    }

}
