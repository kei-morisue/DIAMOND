/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import diamond.model.cyborg.geom.Metric;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.graphics.FaceDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Face<T extends F<T>> implements Serializable, Metric {
    private LinkedList<Ver<T>> vers = new LinkedList<Ver<T>>();
    private HashSet<Seg<T>> creases = new HashSet<Seg<T>>();
    private static final double EPS = 10;

    @Deprecated
    public Face() {
    }

    @SafeVarargs
    public Face(Ver<T>... vers) {
        for (Ver<T> ver : vers) {
            this.vers.add(ver);
        }
    }

    public void add(Line<T> l, Set<Link<T>> links) {
        add(l.clip(links));
    }

    public Ver<T> findVer(double x, double y, double scale) {
        for (Ver<T> ver : vers) {
            if (ver.isNear(x, y, scale)) {
                return ver;
            }
        }
        return null;
    }

    public Seg<T> findSeg(double x, double y, double scale) {
        for (Seg<T> crease : creases) {
            if (crease.isNear(x, y, scale)) {
                return crease;
            }
        }
        return null;
    }

    //TODO
    public Link<T> link(Face<T> f) {
        Ver<T> p = null;
        Ver<T> q = null;
        for (Ver<T> ver : vers) {
            for (Ver<T> wer : f.vers) {
                if (ver == wer) {
                    if (p == null) {
                        p = ver;
                    } else {
                        q = ver;
                    }
                }
            }
        }
        if (p == null || q == null) {
            return null;
        }
        return new Link<T>(this, f, p, q);
    }

    public final void add(Seg<T> seg) {
        HashSet<Ver<T>> vs = new HashSet<Ver<T>>();
        for (Seg<T> s0 : creases) {
            Ver<T> x = s0.xPoint(seg);
            if (x != null) {
                vs.add(x);
                s0.addNode(x);
            }
        }
        for (Ver<T> v : vs) {
            seg.addNode(v);
        }
        creases.add(seg);
    }

    @SafeVarargs
    public final void remove(Seg<T>... segs) {
        for (Seg<T> seg : segs) {
            creases.remove(seg);
        }
    }

    public void draw(ScreenModel<T> screen, Graphics2D g2d) {
        FaceDrawer.draw(screen, g2d, vers, creases);
    }

    public void draw(ScreenCp<T> screen, Graphics2D g2d) {
        FaceDrawer.draw(screen, g2d, vers, creases);
    }

    @Deprecated
    public LinkedList<Ver<T>> getVers() {
        return vers;
    }

    @Deprecated
    public void setVers(LinkedList<Ver<T>> vers) {
        this.vers = vers;
    }

    @Deprecated
    public HashSet<Seg<T>> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<Seg<T>> creases) {
        this.creases = creases;
    }

    private Ver<T> c() {
        F<T> x = null;
        F<T> y = null;
        for (Ver<T> ver : vers) {
            x = (x == null) ? ver.x : x.add(ver.x);
            y = (y == null) ? ver.y : y.add(ver.y);
        }
        int n = vers.size();
        return new Ver<T>(x.div(n), y.div(n));
    }

    @Override
    public double distSquare(double x, double y) {
        return c().distSquare(x, y);
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        return c().isNear(x, y, scale / EPS);
    }

}
