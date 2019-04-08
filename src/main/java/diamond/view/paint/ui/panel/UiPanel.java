/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class UiPanel extends JTabbedPane {
    private ButtonGroup paintActionButtons = new ButtonGroup();

    public UiPanel(PaintContext context) {
        addTab(ResourceHolder.getLabelString(LABEL.CP), buildCpUi(context));
        addTab(ResourceHolder.getLabelString(LABEL.FOLDED),
                buildModelU(context));
    }

    private JPanel buildCpUi(PaintContext context) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new LineTypePanel(context, paintActionButtons));
        panel.add(new EditLinePanel(context, paintActionButtons));
        return panel;
    }

    private JPanel buildModelU(PaintContext context) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new ModelUiPanel(context, paintActionButtons));
        return panel;
    }
}
