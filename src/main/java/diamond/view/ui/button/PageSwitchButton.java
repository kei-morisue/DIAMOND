/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.view.resource.IconSetter;
import diamond.view.screen.PreviewScreen;

/**
 * @author long_
 *
 */
public class PageSwitchButton extends JButton {
    public static final int PREV = -1;
    public static final int NEXT = 1;

    private int direction;
    private PreviewScreen screen;

    public PageSwitchButton(
            PreviewScreen screen,
            Integer direction) {
        setBackground(Color.white);
        this.screen = screen;
        this.direction = direction;
        switch (direction) {
        case PREV:
            IconSetter.set(this, "left.png");
            break;
        default:
            IconSetter.set(this, "right.png");
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            screen.nextPage(direction);
        }
    }
}
