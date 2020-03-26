/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class MainScreen extends AbstractScreen {
    private Context context;

    public MainScreen(Context context) {
        this.context = context;
    }

    @Override
    void draw(Graphics2D g2d) {
        context.getCurrentStep();
    }

}
