/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint;

import java.util.Stack;

import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class ModelContext extends ScreenContext {

    private Stack<OriVertex> pickedOriVertices = new Stack<>();

    public Palette palette;

    public ModelContext(Palette palette) {
        this.palette = palette;
    }

    public Stack<OriVertex> getPickedOriVertices() {
        return pickedOriVertices;
    }

}
