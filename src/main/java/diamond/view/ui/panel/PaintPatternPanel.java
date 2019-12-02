/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.Context;
import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom2Action;
import diamond.controller.action.LazyPaintAction;
import diamond.controller.action.PaintActionInterface;
import diamond.view.resource.string.Labels;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class PaintPatternPanel extends JPanel {
    private ButtonGroup paintPatterns = new ButtonGroup();
    private Context context;

    public PaintPatternPanel(Context context) {
        this.context = context;
        addInputLinePanel();
    }

    private void addInputLinePanel() {
        setLayout(new GridLayout(2, 3));
        JRadioButton button = buildButton("axiom1", new Axiom1Action());
        button.doClick();
        add(button);
        add(buildButton("axiom2", new Axiom2Action()));
        add(buildButton("axiom3", new LazyPaintAction()));//TODO
        add(buildButton("axiom4", new LazyPaintAction()));//TODO
        add(buildButton("symmetric", new LazyPaintAction()));//TODO
        add(buildButton("mirror", new LazyPaintAction()));//TODO
        UiUtil.setBorder(this, Labels.get("input_pattern"));
    }

    private JRadioButton buildButton(String IconBaseName,
            PaintActionInterface paintAction) {
        PaintActionButton button = new PaintActionButton(context, paintAction);
        button.setIcons(IconBaseName);
        paintPatterns.add(button);
        return button;
    }
}
