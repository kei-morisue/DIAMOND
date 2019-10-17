/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.context;

/**
 * @author long_
 *
 */
public class ModelContext extends AbstractScreenContext {

    public ModelContext(Palette palette) {
        super(palette);
        setTransform(palette.getDiagram().getTransform());
    }

}
