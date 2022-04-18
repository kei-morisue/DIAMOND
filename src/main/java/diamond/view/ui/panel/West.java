/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
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
        setLayout(new BorderLayout());
        add(east(context), BorderLayout.EAST);
        add(new FoldedScreen(context), BorderLayout.CENTER);
    }

    private JPanel east(Context context) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(new Tabs(context));
        panel.add(new PaintColorPanel(context));
        return panel;
    }
}
