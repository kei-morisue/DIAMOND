/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.controller.Context;
import diamond.controller.action.PaintActionInterface;

/**
 * @author Kei Morisue
 *
 */
public class PaintActionButton extends AbstractIconButton implements ActionListener {
    private Context context;
    private PaintActionInterface paintAction;

    public PaintActionButton(Context context,
            PaintActionInterface paintAction) {
        this.context = context;
        this.paintAction = paintAction;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            context.initialize();
            context.setPaintAction(paintAction);
        }
    }
}
