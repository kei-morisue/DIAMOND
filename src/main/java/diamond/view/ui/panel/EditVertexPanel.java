/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class EditVertexPanel extends JPanel {
    private ButtonGroup paintActionButtons;

    public EditVertexPanel(PaintContext context,
            ButtonGroup paintActionButtons) {
        this.paintActionButtons = paintActionButtons;
        addEditVertexPanel(context);
    }

    private void addEditVertexPanel(PaintContext context) {
        setLayout(new GridLayout(1, 2));
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.ADD_VERTEX,
                context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.DELETE_VERTEX,
                context);

        UiPanelUtil.setBorder(
                this,
                ResourceHolder.getLabelString(LABEL.EDIT_VERTEX));
    }

}
