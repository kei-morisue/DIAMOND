/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Loop;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.draw.FaceDrawer;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class Face<T extends F<T>> implements Serializable, Graphic<T> {
    private LinkedList<Link<T>> edges = new LinkedList<Link<T>>();
    private HashSet<Seg<T>> creases = new HashSet<Seg<T>>();
    private LinkedList<Ver<T>> vers = new LinkedList<Ver<T>>();

    //    private static final double EPS = 10;
    @Deprecated
    public Face() {
    }

    public Face(List<Link<T>> edges) {
        this.edges.addAll(edges);
        order();
    }

    private void order() {
        vers = Loop.order(this.edges);
    }

    public void add(Link<T> e) {
        edges.add(e);
    }

    public void remove(Link<T> edge) {
        edges.remove(edge);
    }

    public Seg<T> add(Line<T> axiom) {
        Seg<T> clip = axiom.clip(edges);
        if (clip == null) {
            return null;
        }
        for (Seg<T> crease : creases) {
            if (crease.isdubbed(clip)) {
                return null;
            }
        }
        add(clip);
        return clip;
    }

    //TODO must be refactored!!!
    public HashSet<Face<T>> cut(Seg<T> seg, Step<T> step) {
        Link<T> lp = Loop.findNode(edges, seg, true);
        if (lp != null) {
            Face<T> g = lp.cut(seg.getP(), this, step);
            if (g != null) {
                g.order();
            }
        }
        Link<T> lq = Loop.findNode(edges, seg, false);
        if (lq != null) {
            Face<T> g = lq.cut(seg.getQ(), this, step);
            if (g != null) {
                g.order();
            }
        }
        Link<T> link = new Link<T>(seg);
        ArrayList<Link<T>> e0 = new ArrayList<Link<T>>();
        ArrayList<Link<T>> e1 = new ArrayList<Link<T>>();
        e0.add(link);
        e1.add(link);
        for (Link<T> edge : edges) {
            add(edge, e0, seg, e1);
        }
        HashSet<Face<T>> ret = new HashSet<Face<T>>();
        Face<T> f0 = new Face<T>(e0);
        Face<T> f1 = new Face<T>(e1);
        ret.add(f0);
        ret.add(f1);
        f0.order();
        f1.order();
        for (Seg<T> crease : creases) {
            if (crease == seg) {
                continue;
            }
            Ver<T> x = crease.xPoint(seg);
            if (x != null) {
                LinkedList<Seg<T>> cut = crease.cut(x);
                if (cut.size() > 0) {
                    Seg<T> s0 = cut.get(0);
                    add(s0, f0, seg, f1);
                    Seg<T> s1 = cut.get(1);
                    add(s1, f0, seg, f1);
                } else {
                    add(crease, f0, seg, f1);
                }
            } else {
                add(crease, f0, seg, f1);
            }
        }
        return ret;
    }

    private void add(
            Link<T> edge,
            ArrayList<Link<T>> e0,
            Seg<T> seg,
            ArrayList<Link<T>> e1) {
        if (seg.isLeft(edge)) {
            e0.add(edge);
        } else {
            e1.add(edge);
        }
    }

    private void add(
            Seg<T> crease,
            Face<T> f0,
            Seg<T> seg,
            Face<T> f1) {
        if (seg.isLeft(crease)) {
            f0.add(crease);
        } else {
            f1.add(crease);
        }
    }

    public boolean isEdge(Link<T> edge) {
        for (Link<T> e : edges) {
            if (e == edge) {
                return true;
            }
        }
        return false;
    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(edges, creases, vers, x, y, scale);
    }

    public final void add(Seg<T> seg) {
        seg.putNodes(creases);
        creases.add(seg);
    }

    public final void remove(Seg<T> seg) {
        creases.remove(seg);
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        FaceDrawer.draw(screen, g2d, scale, edges, creases, vers, isPointed);
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
        order();
    }

    public LinkedList<Ver<T>> getVers() {
        return vers;
    }

}
