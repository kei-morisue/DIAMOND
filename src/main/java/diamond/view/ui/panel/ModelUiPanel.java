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
public class ModelUiPanel extends JPanel {
    private ButtonGroup paintActionButtons;

    public ModelUiPanel(PaintContext context, ButtonGroup paintActionButtons) {
        this.paintActionButtons = paintActionButtons;
        addModelEditPanel(context);
    }

    private void addModelEditPanel(PaintContext context) {
        setLayout(new GridLayout(2, 2));
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.BASE_FACE,
                context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.FACE_TOP, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.SELECT_VERTEX, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.OFFSET, context);

        UiPanelUtil.setBorder(
                this,
                ResourceHolder.getLabelString(LABEL.FACE));
    }

}
