/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.Context;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class CpSwitch extends JButton {
    public static final int PREV = -1;
    public static final int NEXT = 1;
    public static final int BOTTOM = 0;
    public static final int TOP = 2;

    private int direction;
    private Context context;

    public CpSwitch(int direction, Context context) {
        setBackground(Color.white);
        this.direction = direction;
        this.context = context;
        switch (direction) {
        case PREV:
            IconBuilder.set(this, "left.png");
            break;
        case NEXT:
            IconBuilder.set(this, "right.png");
            break;
        case BOTTOM:
            IconBuilder.set(this, "dleft.png");
            break;
        case TOP:
            IconBuilder.set(this, "dright.png");
            break;
        default:
            setText(Labels.get("no_image"));
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO
            context.getPaintScreen().repaint();
            context.getFoldedScreen().repaint();

        }
    }
}
