/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.awt.Graphics2D;
import java.util.ArrayList;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.draw.StepDrawer;
import diamond.model.cyborg.graphics.find.StepFinder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;
import diamond.view.ui.screen.TransformScreen;

/**
 * @author Kei Morisue
 *
 */
public final class Step<T extends F<T>> {
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
        for (Face<T> face : faces) {
            Seg<T> seg = face.add(axiom);
            face.cut(seg, this);
        }
    }

    //TODO toomuch workload???
    public Face<T> find(Link<T> edge, Face<T> f) {
        for (Face<T> face : faces) {
            if (face.isEdge(edge) && face != f) {
                return face;
            }
        }
        return null;
    }

    public D1<T> findEdge(double x, double y, double scale) {
        return StepFinder.findEdge(faces, x, y, scale);
    }

    public Seg<T> findCrease(double x, double y, double scale) {
        return StepFinder.findCrease(faces, x, y, scale);
    }

    public Ver<T> findVer(double x, double y, double scale) {
        return StepFinder.findVer(faces, x, y, scale);
    }

    public Face<T> findFace(double x, double y, double scale) {
        return StepFinder.findFace(faces, x, y, scale);
    }

    public void draw(ScreenModel<T> screen, Graphics2D g2d) {
        StepDrawer.draw(screen, g2d, faces, baseFace);
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

}
