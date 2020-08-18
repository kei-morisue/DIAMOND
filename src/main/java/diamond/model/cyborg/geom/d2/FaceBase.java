/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.HashSet;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.m.AbstractMirror;

/**
 * @author Kei Morisue
 *
 */
public abstract class FaceBase extends D2 implements Cyborg {
    protected HashSet<SegmentCrease> creases = new HashSet<SegmentCrease>();
    protected AbstractMirror mirror = null;

    protected FaceBase() {
        super();
    }

    public void remove(SegmentCrease crease) {
        creases.remove(crease);
    }

    // TODO to be deprecated
    public HashSet<SegmentCrease> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<SegmentCrease> creases) {
        this.creases = creases;
    }

    public boolean isFlip() {
        return mirror.isFlip();
    }

    public AbstractMirror getMirror() {
        return mirror;
    }

    public void setMirror(AbstractMirror mirror) {
        this.mirror = mirror;
    }

}
