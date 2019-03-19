/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class DiagramSwitchButton extends JButton {
    public static final int LEFT = -1;
    public static final int RIGHT = 1;

    private int direction;
    private PaintContext context;

    public DiagramSwitchButton(Integer direction, PaintContext context) {
        this.direction = direction;
        this.context = context;
        switch (direction) {
        case LEFT:
            setText("<-");
            break;
        default:
            setText("->");
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (context.getStepNo() == 0 && direction == LEFT) {
                return;
            }
            if (context.getCreasePatterns().size() == context.getStepNo()
                    && direction == RIGHT) {
                return;
            }
            context.setStepNo(context.getStepNo() + direction);

        }
    }
}
