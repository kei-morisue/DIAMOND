/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.HashSet;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.d1.CreaseMarger;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.LoopedEdge;
import diamond.model.cyborg.geom.d1.MV;
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
    private LoopedEdge<T> loop = new LoopedEdge<T>();
    private HashSet<Crease<T>> creases = new HashSet<Crease<T>>();
    private HashSet<MV<T>> mvs = new HashSet<MV<T>>();

    //    private static final double EPS = 10;
    public Face() {
    }

    public Face(Edge<T> edge) {
        loop.add(edge);
    }

    public Face(Face<T> f0, Face<T> f1, Edge<T> e) {
        add(f0.loop);
        add(f1.loop);
        remove(e);
        CreaseMarger.marge(
                f0.creases,
                f1.creases,
                this);
        add(new Crease<T>(e));
    }

    public final void add(Crease<T> seg) {
        seg.putNodes(creases);
        creases.add(seg);
    }

    public final void add(
            Collection<Crease<T>> creases) {
        this.creases.addAll(creases);
    }

    public void add(Edge<T> e) {
        loop.add(e);
    }

    public void add(Pair<Edge<T>> pq) {
        loop.add(pq.p);
        loop.add(pq.q);
    }

    public void add(LoopedEdge<T> loop) {
        this.loop.add(loop);
    }

    public final void remove(Crease<T> seg) {
        creases.remove(seg);
    }

    public void remove(Edge<T> edge) {
        loop.remove(edge);
    }

    public MV<T> add(Line<T> axiom) {
        MV<T> clip = clip(axiom);
        if (clip == null) {
            return null;
        }
        mvs.add(clip);
        return clip;
    }

    private MV<T> clip(Line<T> axiom) {
        MV<T> clip = loop.clip(axiom);
        if (clip == null) {
            return null;
        }
        for (Crease<T> crease : creases) {
            if (crease.isdubbed(clip)) {
                return null;
            }
        }
        return clip;
    }

    public boolean isEdge(Edge<T> edge) {
        return loop.isEdge(edge);
    }

    public boolean is(Crease<T> crease) {
        return creases.contains(crease);
    }

    public Pair<Face<T>> cut(Crease<T> seg, Step<T> step) {
        return FaceCutter.cut(loop, creases, seg, step);
    }

    public Face<T> marge(Face<T> face, Edge<T> edge) {
        return FaceMarger.marge(this, face, edge);
    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(loop, creases, mvs, x, y, scale);
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        FaceDrawer.draw(screen, g2d, scale, loop, creases, mvs, isPointed);
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
    public HashSet<Crease<T>> getCreases() {
        return creases;
    }

    @Deprecated
    public void setCreases(HashSet<Crease<T>> creases) {
        this.creases = creases;
    }

    @Deprecated
    public HashSet<MV<T>> getMvs() {
        return mvs;
    }

    @Deprecated
    public void setMvs(HashSet<MV<T>> mvs) {
        this.mvs = mvs;
    }

}
