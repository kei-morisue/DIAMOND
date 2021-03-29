/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.draw.SegDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Seg<T extends F<T>> extends D1<T> implements Serializable {

    @Deprecated
    public Seg() {
    }

    public Seg(Ver<T> p, Ver<T> q) {
        super(p, q);
    }

    public Seg(Ver<T> p, Ver<T> q, List<Ver<T>> nodes) {
        super(p, q, nodes);
    }

    public void cut(Ver<T> r, Face<T> f) {
        LinkedList<Ver<T>> np = null;
        List<Ver<T>> nq = nodes.cut(r, np);
        if (nq == null) {
            return;
        }
        f.remove(this);
        Seg<T> sp = new Seg<T>(p, r, np);
        f.add(sp);
        Seg<T> sq = new Seg<T>(r, q, nq);
        f.add(sq);
    }

    @Override
    public void draw(ScreenModel<T> screen, Graphics2D g2d) {
        nodes.draw(screen, g2d);
        SegDrawer.draw(screen, g2d, p, q, c());
    }

    //TODO toomuch workload???
    public Ver<T> xPoint(Seg<T> s0) {
        Ver<T> v = findVer(s0);
        if (v != null) {
            return v;
        }
        Dir<T> d0 = s0.dir();
        Dir<T> d1 = dir();
        Dir<T> n = d1.n();
        F<T> den0 = n.prod(d0);
        if (den0.isZero()) {
            return null;
        }
        F<T> a = s0.dir(p).prod(n).div(den0);
        F<T> b = dir(s0.q).prod(n).div(den0);
        Dir<T> n0 = d0.n();
        F<T> den1 = n0.prod(d1);
        F<T> c = dir(s0.p).prod(n0).div(den1);
        F<T> d = s0.dir(q).prod(n0).div(den1);
        if (a.isNeg() &&
                b.isNeg() &&
                c.isNeg() &&
                d.isNeg()) {
            return ((Dir<T>) d0.scale(b)).ver(s0.q);
        }
        return null;

    }

    //TBD
    @Override
    public void drawPointed(ScreenModel<T> screen, Graphics2D g2d) {
        SegDrawer.drawPointed(screen, g2d, p, q);
    }

    public void draw(ScreenCp<T> screen, LinkedList<Ver<T>> vers,
            Graphics2D g2d) {
        // TODO 自動生成されたメソッド・スタブ
    }

}
