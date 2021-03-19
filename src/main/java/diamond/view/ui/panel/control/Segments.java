/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public final class Segments extends AbstractButtonPanel {
    JPanel panel = new JPanel();

    public Segments(Context context, ButtonGroup buttonGroup) {
        super(context, buttonGroup);
        setLayout(new GridLayout(1, 3));
        add(buildManagers());
        add(new Colors(context));
        add(new Axioms(context, buttonGroup));
    }

    private JPanel buildManagers() {
        panel.setLayout(new GridLayout(4, 1));

        Util.setBorder(panel, Labels.get("alter_type"));
        return panel;
    }
}
