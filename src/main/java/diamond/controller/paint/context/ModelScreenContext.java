/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.context;

import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public class ModelScreenContext implements ScreenContext {
    protected ScreenTransform transform = new ScreenTransform();

    public ModelScreenContext(Palette palette) {
        setTransform(palette.getDiagram().getTransform());
    }

    @Override
    public ScreenTransform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(ScreenTransform transform) {
        this.transform = transform;
    }

    @Override
    public void initialize() {
    }

}
