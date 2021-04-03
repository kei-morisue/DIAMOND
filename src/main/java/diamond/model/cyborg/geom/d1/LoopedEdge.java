/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.draw.LoopedEdgeDrawer;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class LoopedEdge<T extends F<T>> implements Graphic<T> {
    protected HashSet<Edge<T>> edges = new HashSet<Edge<T>>();
    protected LinkedList<Ver<T>> vers = new LinkedList<Ver<T>>();
    protected boolean isClosed = false;

    public LoopedEdge() {
    }

    @SafeVarargs
    public LoopedEdge(Edge<T>... edges) {
        for (Edge<T> edge : edges) {
            this.edges.add(edge);
        }
    }

    public void add(Edge<T> edge) {
        this.edges.add(edge);
        isClosed = false;
    }

    public void add(LoopedEdge<T> loop) {
        this.edges.addAll(loop.edges);
        isClosed = false;
    }

    public void remove(Edge<T> edge) {
        this.edges.remove(edge);
        isClosed = false;
    }

    public Crease<T> clip(Line<T> axiom) {
        Ver<T> p = null;
        Ver<T> q = null;
        for (Edge<T> link : edges) {
            Ver<T> x = axiom.xPoint(link);
            if (x != null) {
                link.add(x);
                if (p == null) {
                    p = x;
                } else if (q == null) {
                    q = x;
                }
            }
        }
        if (p == null || q == null) {
            return null;
        }
        return new Crease<T>(p, q);
    }

    public void cut(Crease<T> seg, Step<T> step) {
        cut(seg.p, step);
        cut(seg.q, step);
    }

    public void add(
            Crease<T> seg,
            Pair<Face<T>> pq) {
        for (Edge<T> edge : edges) {
            if (seg.isLeft(edge)) {
                pq.p.add(edge);
            } else {
                pq.q.add(edge);
            }
        }
    }

    private boolean order() {
        vers.clear();
        HashSet<Edge<T>> target = new HashSet<Edge<T>>(edges);
        Edge<T> e = edges.iterator().next();
        Ver<T> v = e.p;
        vers.add(v);
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            e = findEdge(v, target);
            if (e != null) {
                v = (e.p == v) ? e.q : e.p;
                vers.add(v);
                target.remove(e);
            } else {
                return false;
            }
        }
        return true;
    }

    private void cut(Ver<T> v, Step<T> step) {
        Edge<T> toBeCut = findNode(v);
        if (toBeCut != null) {
            cut(toBeCut, v, step);
        }
    }

    private void cut(
            Edge<T> edge,
            Ver<T> v,
            Step<T> step) {
        Pair<Edge<T>> pair = edge.cut(v);
        Pair<Face<T>> fg = step.findFaces(edge);
        replace(fg.p, edge, pair);
        replace(fg.q, edge, pair);
    }

    private void replace(
            Face<T> f,
            Edge<T> from,
            Pair<Edge<T>> to) {
        if (f != null) {
            f.add(to);
            f.remove(from);
        }
    }

    private Edge<T> findNode(Ver<T> v) {
        for (Edge<T> e : edges) {
            if (e.isNode(v)) {
                return e;
            }
        }
        return null;
    }

    private Edge<T> findEdge(
            Ver<T> v,
            Collection<Edge<T>> from) {
        for (Edge<T> edge : from) {
            if (edge.p == v || edge.q == v) {
                return edge;
            }
        }
        return null;
    }

    public boolean isEdge(Edge<T> edge) {
        for (Edge<T> e : edges) {
            if (e == edge) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    @Override
    public boolean isNear(double x, double y, double scale) {
        return false;
    }

    @Deprecated
    @Override
    public double distSquare(double x, double y) {
        return 0;
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        if (!isClosed) {
            if (!order()) {
                return;
            }
        }
        LoopedEdgeDrawer.draw(
                screen,
                g2d,
                scale,
                edges,
                vers,
                isPointed);
    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(edges, vers, x, y, scale);
    }

    @Deprecated
    public HashSet<Edge<T>> getEdges() {
        return edges;
    }

    @Deprecated
    public void setEdges(HashSet<Edge<T>> edges) {
        this.edges = edges;
        order();
    }

    public boolean isClosed() {
        return isClosed;
    }

}
