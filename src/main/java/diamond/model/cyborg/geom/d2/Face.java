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

import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Loop;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.graphics.draw.FaceDrawer;
import diamond.model.cyborg.graphics.find.FaceFinder;
import diamond.model.cyborg.graphics.find.Metric;
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

    public void cut(Seg<T> seg, Step<T> step) {
        Link<T> lp = null;
        Link<T> lq = null;
        Ver<T> p = seg.getP();//TODO
        Ver<T> q = seg.getQ();//TODO

        for (Link<T> edge : edges) {
            if (lp == null && edge.isNode(p)) {
                lp = edge;
            }
            if (lq == null && edge.isNode(q)) {
                lq = edge;
            }
        }
        if (lp != null) {
            Face<T> g = step.find(lp, this);
            lp.cut(p, this, g);
        }
        if (lq != null) {
            Face<T> g = step.find(lq, this);
            lq.cut(q, this, g);
        }
        add(new Link<T>(seg));//TODO
        add(new Link<T>(seg));//TODO
        order();
    }

    public boolean isEdge(Link<T> edge) {
        for (Link<T> e : edges) {
            if (e == edge) {
                return true;
            }
        }
        return false;
    }

    public Link<T> findEdge(double x, double y, double scale) {
        return FaceFinder.findEdge(edges, x, y, scale);
    }

    public Ver<T> findVer(double x, double y, double scale) {
        return FaceFinder.findVer(edges, x, y, scale);
    }

    public Seg<T> findCrease(double x, double y, double scale) {
        return FaceFinder.findCrease(creases, x, y, scale);
    }

    public final void add(Seg<T> seg) {
        seg.putNodes(creases);
        creases.add(seg);
    }

    public final void remove(Seg<T> seg) {
        creases.remove(seg);
    }

    public void draw(ScreenModel<T> screen, Graphics2D g2d) {
        FaceDrawer.draw(screen, g2d, edges, creases, vers);
    }

    public void draw(ScreenCp<T> screen, Graphics2D g2d) {
        FaceDrawer.draw(screen, g2d, edges, creases, vers);
    }

    //    public <S extends AbstractScreen<T>> void draw(S screen, Graphics2D g2d) {
    //        FaceDrawer.draw(screen, g2d, edges, creases, vers);
    //    }

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
