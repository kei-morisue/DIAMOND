/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import diamond.controller.paint.context.Context;
import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;
import diamond.view.ui.button.DestroyCpButton;
import diamond.view.ui.button.InsertCpButton;

/**
 * @author long_
 *
 */
public class SouthernBar extends JPanel {
    public SouthernBar(PaintScreen paintScreen, ModelScreen modelScreen) {
        Context context = paintScreen.getContext();
        setLayout(new BorderLayout());
        add(new DestroyCpButton(context), BorderLayout.EAST);
        add(new InsertCpButton(context), BorderLayout.WEST);
    }
}
