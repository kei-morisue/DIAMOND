/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d0.mirror.MirrorPlain;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.draw.LinkDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Link<T extends F<T>> extends D1<T>
        implements Serializable {
    private MirrorPlain<T> mirrorPlain;
    //    private AbstractMirror<T> mirror = null;

    @Deprecated
    public Link() {
    }

    public Link(Ver<T> p, Ver<T> q) {
        super(p, q);
        this.mirrorPlain = new MirrorPlain<T>(p, q);
    }

    public Link(Face<T> f, D1<T> s) {
        this(s.p, s.q);
    }

    public Link(Ver<T> p, Ver<T> q, List<Ver<T>> nodes) {
        super(p, q, nodes);
    }

    public static <S extends F<S>> List<Ver<S>> vers(List<Link<S>> links) {
        LinkedList<Ver<S>> vers = new LinkedList<Ver<S>>();
        for (Link<S> link : links) {
            if (vers.indexOf(link.p) == -1) {
                vers.add(link.p);

            } else {
                vers.add(link.q);
            }
        }
        return vers;
    }

    public boolean isNode(Ver<T> v) {
        return nodes.find(v) != null;
    }

    //TODO
    public void cut(Ver<T> r, Face<T> f, Face<T> g) {
        LinkedList<Ver<T>> np = new LinkedList<Ver<T>>();
        List<Ver<T>> nq = nodes.cut(r, np);
        if (nq == null) {
            return;
        }
        Link<T> lp = new Link<T>(p, r, np);
        Link<T> lq = new Link<T>(r, q, nq);
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

    @Override
    public void draw(ScreenModel<T> screen, Graphics2D g2d) {
        nodes.draw(screen, g2d);
        p.draw(screen, g2d);
        LinkDrawer.draw(screen, g2d, p, q);
    }

    public void draw(ScreenCp<T> screen, Graphics2D g2d) {
        LinkDrawer.draw(screen, g2d, p, q);
    }

    @Override
    public void drawPointed(ScreenModel<T> screen, Graphics2D g2d) {
        nodes.draw(screen, g2d);
        p.draw(screen, g2d);
        LinkDrawer.drawPointed(screen, g2d, p, q);
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
