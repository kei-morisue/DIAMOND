/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import java.awt.Graphics2D;

import diamond.view.ui.screen.ScreenMain;
import diamond.view.ui.screen.ScreenStep;

/**
 * @author Kei Morisue
 *
 */
public interface Graphics {
    public void draw(Graphics2D g2d, ScreenMain screen);

    public void setG2d(Graphics2D g2d, ScreenMain screen);

    public void draw(Graphics2D g2d, ScreenStep screen);

    public void setG2d(Graphics2D g2d, ScreenStep screen);

}
