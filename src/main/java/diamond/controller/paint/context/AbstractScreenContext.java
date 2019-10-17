/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import java.util.Observable;

import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public abstract class AbstractScreenContext extends Observable {
    protected ScreenTransform transform = new ScreenTransform();
    protected Palette palette;

    public AbstractScreenContext(Palette palette) {
        this.palette = palette;
    }

    public Palette getPalette() {
        return palette;
    }

    public void setPalette(Palette palette) {
        this.palette = palette;
    }

    public ScreenTransform getTransform() {
        return transform;
    }

    public void setTransform(ScreenTransform transform) {
        this.transform = transform;
    }

}
