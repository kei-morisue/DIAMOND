/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import java.util.Observable;

/**
 * @author long_
 *
 */
public class Context extends Observable {
    private ModelScreenContext modelScreenContext;
    private PaintScreenContext paintScreenContext;
    private Palette palette;

    public Context(Palette palette) {
        this.palette = palette;
        modelScreenContext = new ModelScreenContext(palette);
        paintScreenContext = new PaintScreenContext(palette);
    }

    public Palette getPalette() {
        return palette;
    }

    public ModelScreenContext getModelScreenContext() {
        return this.modelScreenContext;
    }

    public PaintScreenContext getPaintScreenContext() {
        return this.paintScreenContext;
    }

    public void initialize() {
        paintScreenContext.initialize();
    }
}
