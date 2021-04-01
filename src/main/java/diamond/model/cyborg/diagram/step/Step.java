/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.draw.StepDrawer;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.TransformScreen;

/**
 * @author Kei Morisue
 *
 */
public final class Step<T extends F<T>> implements Graphic<T> {
    protected ArrayList<Face<T>> faces = new ArrayList<>();
    protected Face<T> baseFace;
    protected TransformScreen transform = new TransformScreen();

    @Deprecated
    public Step() {
    }

    public Step(ArrayList<Face<T>> faces) {
        this.faces = faces;
        baseFace = faces.get(0);
    }

    public Step(Step<T> step) {
        faces = step.faces;
        baseFace = step.baseFace;
        transform = step.transform;//TODO
    }

    public void add(Line<T> axiom) {
        for (Face<T> face : faces) {
            face.add(axiom);
        }
    }

    public void cut(Line<T> axiom) {
        HashSet<Face<T>> fs = new HashSet<Face<T>>();
        for (Face<T> face : faces) {
            Seg<T> seg = face.add(axiom);
            if (seg != null) {
                face.cut(seg, this).add(fs);
            } else {
                fs.add(face);
            }
        }
        faces.clear();
        faces.addAll(fs);//TODO order faces
    }

    public void marge(D1<T> t) {
        Link<T> edge = null;
        try {
            edge = (Link<T>) t;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return;
        }
        Pair<Face<T>> fg = findPair(edge);
        Face<T> h = fg.p.marge(fg.q, edge);
        faces.remove(fg.p);
        faces.remove(fg.q);
        faces.add(h);
    }

    //TODO toomuch workload???
    public Pair<Face<T>> findPair(Link<T> edge) {
        Face<T> f = null;
        for (Face<T> face : faces) {
            if (face.isEdge(edge)) {
                if (f == null) {
                    f = face;
                } else {
                    return new Pair<Face<T>>(f, face);
                }
            }
        }
        return new Pair<Face<T>>(f, null);
    }

    @Override
    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(faces, baseFace, x, y, scale);
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        StepDrawer.draw(screen, g2d, scale, faces, baseFace);
    }

    @Deprecated
    public ArrayList<Face<T>> getFaces() {
        return faces;
    }

    @Deprecated
    public void setFaces(ArrayList<Face<T>> faces) {
        this.faces = faces;
    }

    @Deprecated
    public Face<T> getBaseFace() {
        return baseFace;
    }

    @Deprecated
    public void setBaseFace(Face<T> baseFace) {
        this.baseFace = baseFace;
    }

    @Deprecated
    public TransformScreen getTransform() {
        return transform;
    }

    @Deprecated
    public void setTransform(TransformScreen transform) {
        this.transform = transform;
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        // TODO 自動生成されたメソッド・スタブ
        return false;
    }

    @Override
    public double distSquare(double x, double y) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

}
