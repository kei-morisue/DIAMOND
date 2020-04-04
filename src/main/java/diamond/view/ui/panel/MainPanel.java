/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import diamond.view.ui.screen.MainScreen;

/**
 * @author Kei Morisue
 *
 */
public class MainPanel extends JPanel {
    public MainPanel(MainScreen mainScreen) {
        setLayout(new GridLayout(1, 1));
        setBackground(Color.BLUE);
        add(mainScreen);
    }
}
