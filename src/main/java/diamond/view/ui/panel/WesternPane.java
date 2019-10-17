/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import diamond.controller.paint.context.PaintContext;
import diamond.view.screen.ModelScreen;

/**
 * @author long_
 *
 */
public class WesternPane extends JPanel {
    public WesternPane(PaintContext paintContext, ModelScreen modelScreen) {
        setLayout(new GridLayout(2, 1));
        add(modelScreen);
        add(new UiPanel(paintContext));

    }
}
