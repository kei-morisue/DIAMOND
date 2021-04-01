/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d0.mirror.MirrorPlain;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.draw.LinkDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class Link<T extends F<T>> extends D1<T> {
    private MirrorPlain<T> mirrorPlain;
    //    private AbstractMirror<T> mirror = null;

    @Deprecated
    public Link() {
    }

    public Link(Ver<T> p, Ver<T> q) {
        super(p, q);
        this.mirrorPlain = new MirrorPlain<T>(p, q);
    }

    public Link(Seg<T> seg) {
        this(seg.p, seg.q);
        this.nodes = seg.nodes;
    }

    @Override
    public void add(Face<T> face) {
        face.add(this);
    }

    public Pair<Link<T>> cut(Ver<T> r) {
        if (!isNode(r)) {
            return null;
        }
        Link<T> lp = new Link<T>(p, r);
        Link<T> lq = new Link<T>(r, q);
        cut(lp, r, lq);
        return new Pair<Link<T>>(lp, lq);
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        LinkDrawer.draw(screen, g2d, scale, p, q, nodes, isPointed);
    }

    @Deprecated
    public MirrorPlain<T> getMirrorPlain() {
        return mirrorPlain;
    }

    @Deprecated
    public void setMirrorPlain(MirrorPlain<T> mirrorPlain) {
        this.mirrorPlain = mirrorPlain;
    }

}
