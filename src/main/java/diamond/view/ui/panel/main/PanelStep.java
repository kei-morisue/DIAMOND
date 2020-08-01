/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.button.ButtonStep;

/**
 * @author Kei Morisue
 *
 */
public class PanelStep extends JPanel {
    private Context context;

    public PanelStep(Context context) {
        this.context = context;
        setLayout(new BorderLayout());
        add(new ButtonStep(ButtonStep.BOTTOM, context),
                BorderLayout.WEST);
        add(new ButtonStep(ButtonStep.TOP, context),
                BorderLayout.EAST);
        add(buildCenter(context), BorderLayout.CENTER);
    }

    private JPanel buildCenter(Context context) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new ButtonStep(ButtonStep.PREV, context));
        panel.add(new ButtonStep(ButtonStep.NEXT, context));
        return panel;
    }
}
