/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class Tabs extends JTabbedPane {
    private ButtonGroup buttons = new ButtonGroup();

    public Tabs(Context context) {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 5));
        p.add(new TabManageFaces(context, buttons));
        p.add(new TabPaintSymbols(context, buttons));
        p.add(new PaintColorPanel(context));
        p.add(new TabAlterLineType(context, buttons));
        p.add(new PaintPatternPanel(context, buttons));

        addTab("lines / symbols", p);
        //        addTab(Labels.get("tab_vertex"),
        //                new TabManageVertices(context, buttons));

    }
}
