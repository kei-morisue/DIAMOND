/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public class Axioms extends AbstractButtonPanel {

    public Axioms(Context context, ButtonGroup buttonGroup) {
        super(context, buttonGroup);
        addInputLinePanel();
    }

    private void addInputLinePanel() {
        setLayout(new GridLayout(2, 3));
        Util.setBorder(this, Labels.get("input_pattern"));
    }
}
