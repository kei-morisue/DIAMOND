/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.paint.context.ModelContext;
import diamond.controller.paint.context.Palette;
import diamond.view.resource.IconSetter;

/**
 * @author long_
 *
 */
public class DiagramSwitchButton extends JButton {
    public static final int PREV = -1;
    public static final int NEXT = 1;

    private int direction;
    private ModelContext context;

    public DiagramSwitchButton(Integer direction, ModelContext context) {
        setBackground(Color.white);
        this.direction = direction;
        this.context = context;
        switch (direction) {
        case PREV:
            IconSetter.set(this, "left.gif");
            break;
        default:
            IconSetter.set(this, "right.gif");
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Palette palette = context.palette;
            if (palette.getStepNo() == 0 && direction == PREV) {
                return;
            }
            if (palette.size() == palette.getStepNo()
                    && direction == NEXT) {
                return;
            }
            palette.getDiagram().setTransform(context.transform);
            palette.setStepNo(palette.getStepNo() + direction);
            context.transform = palette.getDiagram().getTransform();
            context.screen.repaint();

        }
    }
}
