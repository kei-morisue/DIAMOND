/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;

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

    public void cut(Ver<T> r, Face<T> f, Face<T> g) {
        if (!nodes.isNode(r)) {
            return;
        }
        Link<T> lp = new Link<T>(p, r);
        Link<T> lq = new Link<T>(r, q);
        nodes.cut(r, p, q, lp, lq);
        if (f != null) {
            f.remove(this);
            f.add(lp);
            f.add(lq);
        }
        if (g != null) {
            g.remove(this);
            g.add(lp);
            g.add(lq);
        }
    }

    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            boolean isPointed) {
        nodes.draw(screen, g2d, isPointed);
        LinkDrawer.draw(screen, g2d, p, q, isPointed);
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
