/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class UiPanel extends JPanel {
    private ButtonGroup paintActionButtons = new ButtonGroup();

    public UiPanel(PaintContext context) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new LineTypePanel(context));
        add(new InputLinePanel(context, paintActionButtons));
        add(new EditLinePanel(context, paintActionButtons));
        add(new ModelUiPanel(context, paintActionButtons));
    }

}
