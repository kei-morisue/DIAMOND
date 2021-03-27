/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
    private LinkedList<Link<T>> edges = new LinkedList<Link<T>>();
    private HashSet<Seg<T>> creases = new HashSet<Seg<T>>();
    //    private static final double EPS = 10;

    public Face() {
    }

    public void add(Link<T> edge) {
        edges.add(edge);
    }

    public Face(List<Link<T>> edges) {
        edges.addAll(edges);
    }

    public void add(Line<T> axiom) {
        add(axiom.clip(edges));
    }

    public Ver<T> findVer(double x, double y, double scale) {
        for (Link<T> edge : edges) {
            Ver<T> ver = edge.findVer(x, y, scale);
            if (ver != null) {
                return ver;
            }
        }
        return null;
    }

    public Link<T> findLink(double x, double y, double scale) {
        for (Link<T> edge : edges) {
            if (edge.isNear(x, y, scale)) {
                return edge;
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
        FaceDrawer.draw(screen, g2d, edges, creases);
    }

    public void draw(ScreenCp<T> screen, Graphics2D g2d) {
        FaceDrawer.draw(screen, g2d, edges, creases);
    }

    @Deprecated
    public HashSet<Seg<T>> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<Seg<T>> creases) {
        this.creases = creases;
    }

    @Override
    public double distSquare(double x, double y) {
        //        TODO
        return 0;
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        //        TODO
        return false;
    }

    @Deprecated
    public LinkedList<Link<T>> getEdges() {
        return edges;
    }

    @Deprecated
    public void setEdges(LinkedList<Link<T>> edges) {
        this.edges = edges;
    }

}
