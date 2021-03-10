/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;
import diamond.view.ui.screen.TransformScreen;

/**
 * @author Kei Morisue
 *
 */
public final class Step<T extends F<T>> {
    protected ArrayList<Face<T>> faces = new ArrayList<>();
    protected Face<T> baseFace;
    protected HashSet<Link<T>> links = new HashSet<>();
    protected TransformScreen transform = new TransformScreen();

    @Deprecated
    public Step() {
    }

    public Step(
            ArrayList<Face<T>> faces,
            HashSet<Link<T>> links) {
        this.faces = faces;
        baseFace = faces.get(0);
        this.links = links;
    }

    public Step(Step<T> step) {
        faces = step.faces;
        baseFace = step.baseFace;
        links = step.links;
        transform = step.transform;
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
    public HashSet<Link<T>> getLinks() {
        return links;
    }

    @Deprecated
    public void setLinks(HashSet<Link<T>> links) {
        this.links = links;
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
