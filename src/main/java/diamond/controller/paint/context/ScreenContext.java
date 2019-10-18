/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public interface ScreenContext {
    public ScreenTransform getTransform();

    public void setTransform(ScreenTransform transform);

    public void initialize();
}
