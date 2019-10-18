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
public class AlterLineTypeUiPanel extends JPanel {
    private ButtonGroup paintActionButtons;

    public AlterLineTypeUiPanel(PaintScreenContext context, ButtonGroup paintActionButtons) {
        this.paintActionButtons = paintActionButtons;
        addEditLineTypePanel(context);
    }

    private void addEditLineTypePanel(PaintScreenContext context) {
        setLayout(new GridLayout(2, 3));
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.FLIP_LINE_TYPE,
                context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.SETTLE_UNSETTLE,
                context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.FOLD_UNFOLD, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.DELETE_LINE, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.CONTOUR, context);
    }

}
