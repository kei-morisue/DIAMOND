/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

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
public class InputLinePanel extends JPanel {
    ButtonGroup paintActionButtons;

    public InputLinePanel(PaintContext context,
            ButtonGroup paintActionButtons) {
        this.paintActionButtons = paintActionButtons;
        addInputLinePanel(context);
    }

    private void addInputLinePanel(PaintContext context) {
        setLayout(new GridLayout(2, 3));
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.AXIOM1, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,
                LABEL.AXIOM2, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,

                LABEL.AXIOM3, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,

                LABEL.AXIOM4, context);
        UiPanelUtil.addPaintActionButton(this, paintActionButtons,

                LABEL.SYMMETRIC, context);
        UiPanelUtil.setBorder(
                this,
                ResourceHolder.getLabelString(LABEL.LINE_PATTERN));
    }

}
