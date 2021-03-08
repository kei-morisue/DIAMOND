/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.screen.ScreenMain;

/**
 * @author Kei Morisue
 *
 */
public class PanelCp extends JPanel {
    private ScreenMain screen;

    public PanelCp(Context context) {
        setLayout(new BorderLayout());
        setBackground(Color.BLUE);
        screen = new ScreenMain(context);
        add(new PanelStep(context), BorderLayout.NORTH);
        add(screen, BorderLayout.CENTER);
        add(new PanelAdd(context), BorderLayout.SOUTH);

    }
}
