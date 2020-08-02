/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.controller.action.paint.Lazy;
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
        add(buildButton("axiom1", new Lazy()));//TODO
        add(buildButton("axiom2", new Lazy()));//TODO
        add(buildButton("axiom3", new Lazy()));//TODO
        add(buildButton("axiom4", new Lazy()));//TODO
        add(buildButton("symmetric", new Lazy()));//TODO
        add(buildButton("mirror", new Lazy()));//TODO
        Util.setBorder(this, Labels.get("input_pattern"));
    }
}
