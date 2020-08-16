/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.screen.ScreenStep;

/**
 * @author Kei Morisue
 *
 */
public class PanelControl extends JPanel {

    private ButtonGroup buttons = new ButtonGroup();
    private JTabbedPane control = new JTabbedPane();

    public PanelControl(Context context) {
        setLayout(new GridLayout(2, 1));
        setBackground(Color.RED);
        add(new ScreenStep(context));
        add(buildControl(context));
    }

    private JTabbedPane buildControl(Context context) {
        control.addTab(Labels.get("tab_paint_lines"),
                new Segments(context, buttons));
        //        control.addTab(Labels.get("tab_alter_type"),
        //                new TabAlterLineType(context, buttons));
        //        control.addTab(Labels.get("tab_faces"),
        //                new TabManageFaces(context, buttons));
        //        control.addTab(Labels.get("tab_vertex"),
        //                new TabManageVertices(context, buttons));
        //        control.addTab(Labels.get("tab_paint_symbols"),
        //                new TabPaintSymbols(context, buttons));
        return control;
    }
}
