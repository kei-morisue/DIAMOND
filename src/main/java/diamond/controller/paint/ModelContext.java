/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint;

/**
 * @author long_
 *
 */
public class ModelContext extends ScreenContext {

    public ModelContext(Palette palette) {
        super(palette);
        transform = palette.getDiagram().getTransform();
    }

}
