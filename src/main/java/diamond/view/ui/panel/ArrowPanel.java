/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class ArrowPanel extends JPanel {

    public ArrowPanel(PaintContext context, ButtonGroup buttonGroup) {
        UiPanelUtil.addPaintActionButton(this, buttonGroup,
                LABEL.VALLEY_ARROW, context);
    }
}
