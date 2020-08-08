/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.controller.Context;
import diamond.controller.action.paint.AbstractPaintAction;

/**
 * @author Kei Morisue
 *
 */
public class ButtonPaintAction extends AbstractIconButton
        implements ActionListener {
    private Context context;
    private AbstractPaintAction paintAction;

    public ButtonPaintAction(Context context,
            AbstractPaintAction paintAction) {
        this.context = context;
        this.paintAction = paintAction;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            context.initialize();
            context.setPaintAction(this.paintAction);
        }
    }
}