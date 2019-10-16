/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import java.util.Observable;

import diamond.view.screen.AbstractScreen;
import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public class ScreenContext extends Observable {
    public AbstractScreen screen;
    public Palette palette;
    public ScreenTransform transform = new ScreenTransform(0,
            0);

    public ScreenContext(Palette palette) {
        this.palette = palette;

    }

    public double getScale() {
        return transform.getScale();
    }

}
