/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.screen.ScreenMain;

/**
 * @author Kei Morisue
 *
 */
public class PanelControl extends JPanel {
    public PanelControl(Context context) {
        setLayout(new GridLayout(2, 1));
        setBackground(Color.RED);
        add(new ScreenMain(context));
    }
}
