/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author Kei Morisue
 *
 */
public class Util {

    public static boolean isControlKeyPressed(MouseEvent e) {
        return ((e.getModifiersEx()
                & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK);
    }

    public static boolean isLeftClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON1;
    }

    public static boolean isRightClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON3;
    }

    public static boolean isCtrl(KeyEvent e) {
        return ((e.getModifiersEx()
                & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK);
    }

    private Util() {
    }
}
