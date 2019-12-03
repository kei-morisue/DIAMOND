package diamond.view.ui.panel;

import java.awt.GridLayout;

/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class TabPaintLines extends JPanel {
    public TabPaintLines(Context context, ButtonGroup buttonGroup) {
        setLayout(new GridLayout(1, 2));
        add(new PaintPatternPanel(context, buttonGroup));
        add(new PaintColorPanel(context));
    }

}
