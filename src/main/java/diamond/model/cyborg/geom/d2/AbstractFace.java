/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.Pair;
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
public abstract class AbstractFace<T extends F<T>>
        implements Graphic<T> {
    protected LinkedList<Link<T>> edges = new LinkedList<Link<T>>();
    protected HashSet<Seg<T>> creases = new HashSet<Seg<T>>();
    protected LinkedList<Ver<T>> vers = new LinkedList<Ver<T>>();

    //    private static final double EPS = 10;
    @Deprecated
    public AbstractFace() {
    }

    public AbstractFace(Link<T> edge) {
        this.edges.add(edge);
    }

    protected void order() {
        vers = Loop.order(this.edges);
    }

    public final void add(Seg<T> seg) {
        seg.putNodes(creases);
        creases.add(seg);
    }

    public void add(Link<T> e) {
        edges.add(e);
        order();
    }

    public void add(Pair<Link<T>> pq) {
        pq.add(edges);
        order();
    }

    public void add(Collection<Link<T>> edges) {
        this.edges.addAll(edges);
        order();
    }

    public final void remove(Seg<T> seg) {
        creases.remove(seg);
    }

    public void remove(Link<T> edge) {
        edges.remove(edge);
    }

    public Seg<T> add(Line<T> axiom) {
        Seg<T> clip = clip(axiom);
        add(clip);
        return clip;
    }

    private Seg<T> clip(Line<T> axiom) {
        Seg<T> clip = axiom.clip(edges);
        if (clip == null) {
            return null;
        }
        for (Seg<T> crease : creases) {
            if (crease.isdubbed(clip)) {
                return null;
            }
        }
        return clip;
    }

    public boolean isEdge(Link<T> edge) {
        for (Link<T> e : edges) {
            if (e == edge) {
                return true;
            }
        }
        return false;
    }

    public Link<T> find(Face<T> f) {
        for (Link<T> e : edges) {
            if (isEdge(e)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(edges, creases, vers, x, y, scale);
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
