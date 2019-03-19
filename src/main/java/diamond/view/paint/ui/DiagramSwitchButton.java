/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ImageIconLoader;

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
        setBackground(Color.white);
        this.direction = direction;
        this.context = context;
        ImageIconLoader imgLoader = new ImageIconLoader();
        switch (direction) {
        case LEFT:
            setIcon(imgLoader.loadAsIcon("icon/left.gif"));
            break;
        default:
            setIcon(imgLoader.loadAsIcon("icon/right.gif"));
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
