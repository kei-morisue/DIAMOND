/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class Tabs extends JTabbedPane {
    private ButtonGroup buttons = new ButtonGroup();

    public Tabs(Context context) {
        addTab(Labels.get("tab_paint_lines"),
                new TabPaintLines(context, buttons));
        addTab(Labels.get("tab_alter_type"),
                new TabAlterLineType(context, buttons));
        addTab(Labels.get("tab_vertex"),
                new TabManageVertices(context, buttons));
        addTab(Labels.get("tab_faces"),
                new TabManageFaces(context, buttons));
        addTab(Labels.get("tab_paint_symbols"),
                new TabPaintSymbols(context, buttons));

    }
}
