/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;

/**
 * @author long_
 *
 */
public class EasternPane extends JPanel {
    public EasternPane(PaintScreen paintScreen, ModelScreen modelScreen) {
        setLayout(new BorderLayout());
        add(paintScreen, BorderLayout.CENTER);
        add(new NorthernBar(paintScreen, modelScreen), BorderLayout.NORTH);
        add(new SouthernBar(paintScreen, modelScreen), BorderLayout.SOUTH);
    }
}
