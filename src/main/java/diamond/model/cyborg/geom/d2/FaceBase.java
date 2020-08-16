/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.HashSet;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentEdge;
import diamond.model.cyborg.geom.m.Mirror;

/**
 * @author Kei Morisue
 *
 */
public abstract class FaceBase extends D2 implements Cyborg {
    protected HashSet<SegmentEdge> edges = new HashSet<SegmentEdge>();
    protected HashSet<SegmentCrease> creases = new HashSet<SegmentCrease>();
    protected Mirror mirror = null;

    public FaceBase() {
        super();
    }

    public void add(SegmentEdge edge) {
        edges.add(edge);
    }

    public void remove(SegmentCrease crease) {
        creases.remove(crease);
    }

    public void remove(SegmentEdge edge) {
        edges.remove(edge);
    }

    // TODO to be deprecated
    public HashSet<SegmentCrease> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<SegmentCrease> creases) {
        this.creases = creases;
    }

    public boolean isFront() {
        return mirror.isFlip();
    }

    public HashSet<SegmentEdge> getEdges() {
        return edges;
    }

    public Mirror getMirror() {
        return mirror;
    }

    public void setMirror(Mirror mirror) {
        this.mirror = mirror;
    }

}
