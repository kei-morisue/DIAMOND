/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JTabbedPane;

import diamond.controller.paint.context.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class UiPanel extends JTabbedPane {
    private ButtonGroup buttons = new ButtonGroup();

    public UiPanel(PaintContext context) {
        addTab(ResourceHolder.getLabelString(LABEL.PAINT_LINES_TAB),
                new PaintLinesUiPanel(context, buttons));
        addTab(ResourceHolder.getLabelString(LABEL.ALTER_LINE_TYPE_TAB),
                new AlterLineTypeUiPanel(context, buttons));
        addTab(ResourceHolder.getLabelString(LABEL.SIGNS_TAB),
                new SignsUiPanel(context, buttons));
        addTab(ResourceHolder.getLabelString(LABEL.OFFSET_VERTEX_TAB),
                new EditVertexUiPanel(context, buttons));
        addTab(ResourceHolder.getLabelString(LABEL.FACE_MANAGEMENT_TAB),
                new ModelUiPanel(context, buttons));
    }
}
