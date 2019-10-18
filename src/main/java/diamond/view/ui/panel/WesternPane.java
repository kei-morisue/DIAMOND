/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import diamond.controller.paint.context.Context;
import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;

/**
 * @author long_
 *
 */
public class WesternPane extends JPanel {
    public WesternPane(PaintScreen paintScreen, ModelScreen modelScreen) {
        setLayout(new GridLayout(2, 1));
        add(modelScreen);
        Context context = paintScreen.getContext();
        add(new UiPanel(context.getPaintScreenContext()));

    }
}
