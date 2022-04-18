/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.view.resource.IconBuilder;
import diamond.view.ui.screen.PreviewScreen;

/**
 * @author Kei Morisue
 *
 */
public class PageSwitch extends JButton {
    public static final int PREV = -1;
    public static final int NEXT = 1;

    private int direction;
    private PreviewScreen screen;

    public PageSwitch(
            PreviewScreen screen,
            Integer direction) {
        setBackground(Color.white);
        setFocusable(false);
        this.screen = screen;
        this.direction = direction;
        switch (direction) {
        case PREV:
            IconBuilder.set(this, "left.png");
            break;
        default:
            IconBuilder.set(this, "right.png");
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
