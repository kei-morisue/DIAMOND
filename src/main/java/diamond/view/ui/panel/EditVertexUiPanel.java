/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.context.PaintScreenContext;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class EditVertexUiPanel extends JPanel {
    private ButtonGroup paintActionButtons;

    public EditVertexUiPanel(PaintScreenContext context,
            ButtonGroup paintActionButtons) {
        this.paintActionButtons = paintActionButtons;
        addEditVertexPanel(context);
    }

    private void addEditVertexPanel(PaintScreenContext context) {
        setLayout(new GridLayout(2, 3));
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.ADD_VERTEX,
                context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.DELETE_VERTEX,
                context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.SELECT_VERTEX, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.SELECT_ALL_VERTEX, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.OFFSET, context);

    }

}
