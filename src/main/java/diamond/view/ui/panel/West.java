/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.screen.FoldedScreen;

/**
 * @author Kei Morisue
 *
 */
public class West extends JPanel {

    public West(Context context) {
        setLayout(new GridLayout(2, 1));
        add(new FoldedScreen(context));
        add(new PaintButtons(context));
    }
}
