/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.PaintContext;
import diamond.view.paint.ui.panel.EditLinePanel;
import diamond.view.paint.ui.panel.InputLinePanel;
import diamond.view.paint.ui.panel.LineTypePanel;
import diamond.view.paint.ui.panel.ModelUiPanel;

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
