/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.util.HashSet;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.LoopedEdge;
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
public final class Face<T extends F<T>> implements Graphic<T> {
    protected LoopedEdge<T> loop;
    protected HashSet<Seg<T>> creases = new HashSet<Seg<T>>();

    //    private static final double EPS = 10;
    @Deprecated
    public Face() {
    }

    public Face(Edge<T> edge) {
        loop = new LoopedEdge<T>(edge);
    }

    public final void add(Seg<T> seg) {
        seg.putNodes(creases);
        creases.add(seg);
    }

    public void add(Edge<T> e) {
        loop.add(e);
    }

    public void add(Pair<Edge<T>> pq) {
        loop.add(pq.p);
        loop.add(pq.q);
    }

    public final void remove(Seg<T> seg) {
        creases.remove(seg);
    }

    public void remove(Edge<T> edge) {
        loop.remove(edge);
    }

    public Seg<T> add(Line<T> axiom) {
        Seg<T> clip = clip(axiom);
        add(clip);
        return clip;
    }

    private Seg<T> clip(Line<T> axiom) {
        Seg<T> clip = loop.clip(axiom);
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

    public boolean isEdge(Edge<T> edge) {
        return loop.isEdge(edge);
    }

    public Pair<Face<T>> cut(Seg<T> seg, Step<T> step) {
        return FaceCutter.cut(loop, creases, seg, step);
    }

    public Face<T> marge(Face<T> face, Edge<T> edge) {
        return FaceMarger.marge(face, edge);
    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(loop, creases, x, y, scale);
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        FaceDrawer.draw(screen, g2d, scale, loop, creases, isPointed);
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
    public LoopedEdge<T> getLoop() {
        return loop;
    }

    @Deprecated
    public void setLoop(LoopedEdge<T> loop) {
        this.loop = loop;
    }

    @Deprecated
    public HashSet<Seg<T>> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<Seg<T>> creases) {
        this.creases = creases;
    }

}
