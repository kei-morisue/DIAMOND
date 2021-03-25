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
import diamond.model.cyborg.graphics.LinkDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Link<T extends F<T>> extends D1<T>
        implements Serializable {
    private Face<T> f;
    private MirrorPlain<T> mirrorPlain;
    //    private AbstractMirror<T> mirror;

    @Deprecated
    public Link() {
    }

    public Link(Face<T> f, Ver<T> p, Ver<T> q) {
        super(p, q);
        this.f = f;
        f.add(this);
        this.mirrorPlain = new MirrorPlain<T>(p, q);
    }

    public static <S extends F<S>> List<Ver<S>> vers(List<Link<S>> links) {
        LinkedList<Ver<S>> vers = new LinkedList<Ver<S>>();
        for (Link<S> link : links) {
            vers.add(link.p);
        }
        return vers;
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
    public Ver<T> getP() {
        return p;
    }

    @Deprecated
    public void setP(Ver<T> p) {
        this.p = p;
    }

    @Deprecated
    public Ver<T> getQ() {
        return q;
    }

    @Deprecated
    public void setQ(Ver<T> q) {
        this.q = q;
    }

    @Deprecated
    public Face<T> getF() {
        return f;
    }

    @Deprecated
    public void setF(Face<T> f) {
        this.f = f;
    }

    @Deprecated
    public MirrorPlain<T> getMirrorPlain() {
        return mirrorPlain;
    }

    @Deprecated
    public void setMirrorPlain(MirrorPlain<T> mirrorPlain) {
        this.mirrorPlain = mirrorPlain;
    }

    @Override
    public void cut(Face<T> face, int i) {
        // TODO 自動生成されたメソッド・スタブ

    }

}
