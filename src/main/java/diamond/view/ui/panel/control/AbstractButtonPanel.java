/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.Context;
import diamond.view.ui.button.ButtonPaintAction;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractButtonPanel extends JPanel {
    protected Context context;
    protected ButtonGroup group;

    public AbstractButtonPanel(Context context, ButtonGroup group) {
        this.context = context;
        this.group = group;
    }

    protected JRadioButton buildButton(String IconBaseName) {
        ButtonPaintAction button = new ButtonPaintAction(context);
        button.setIcons(IconBaseName);
        group.add(button);
        return button;
    }
}
