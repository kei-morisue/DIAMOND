/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.paint.context.PaintContext;

/**
 * @author long_
 *
 */
public class UiPanel extends JPanel {
    private ButtonGroup buttons = new ButtonGroup();

    public UiPanel(PaintContext context) {
        buildCpUi(context);
    }

    private void buildCpUi(PaintContext context) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new LineTypePanel(context, buttons));
        add(new EditLinePanel(context, buttons));
        add(new DiagramUiPanel(context, buttons));
        add(new EditVertexPanel(context, buttons));
        add(new ModelUiPanel(context, buttons));
    }

}
