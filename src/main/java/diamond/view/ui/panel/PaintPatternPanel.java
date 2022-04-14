/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.Context;
import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom2Action;
import diamond.controller.action.Axiom3Action;
import diamond.controller.action.Axiom4Action;
import diamond.controller.action.Axiom5Action;
import diamond.controller.action.PaintActionInterface;
import diamond.controller.action.SymmetricAction;
import diamond.view.resource.string.Labels;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class PaintPatternPanel extends JPanel {
    private ButtonGroup buttonGroup;
    private Context context;

    public PaintPatternPanel(Context context, ButtonGroup buttonGroup) {
        this.context = context;
        this.buttonGroup = buttonGroup;
        addInputLinePanel();
    }

    private void addInputLinePanel() {
        setLayout(new GridLayout(6, 1));
        JRadioButton button = buildButton("axiom1", new Axiom1Action());
        button.doClick();
        add(button);
        add(buildButton("axiom2", new Axiom2Action()));
        add(buildButton("axiom3", new Axiom3Action()));
        add(buildButton("axiom4", new Axiom4Action()));
        add(buildButton("axiom5", new Axiom5Action()));
        add(buildButton("symmetric", new SymmetricAction()));
        UiUtil.setBorder(this, Labels.get("input_pattern"));
    }

    private JRadioButton buildButton(String IconBaseName,
            PaintActionInterface paintAction) {
        PaintActionButton button = new PaintActionButton(context, paintAction);
        button.setIcons(IconBaseName);
        button.setSize(new Dimension(200, 200));
        buttonGroup.add(button);
        return button;
    }
}
