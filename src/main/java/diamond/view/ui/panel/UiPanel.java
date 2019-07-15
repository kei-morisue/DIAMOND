/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

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
        buildCpUi(context);
    }

    private void buildCpUi(PaintContext context) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new LineTypePanel(context, paintActionButtons));
        add(new EditLinePanel(context, paintActionButtons));
        add(new DiagramUiPanel(context, paintActionButtons));
        add(new EditVertexPanel(context, paintActionButtons));
        add(new ModelUiPanel(context, paintActionButtons));
    }

}
