/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.mouse;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.view.ui.screen.TransformScreen;

/**
 * @author Kei Morisue
 *
 */
public class Util {
    public static Vertex getLogicalPoint(TransformScreen transform,
            Point mousePoint) {
        Point2D.Double logicalPoint = new Point2D.Double();
        transform.getInverse().transform(mousePoint, logicalPoint);
        return new Vertex(logicalPoint.x, logicalPoint.y);
    }

    public static boolean isControlKeyPressed(MouseEvent e) {
        return ((e.getModifiersEx()
                & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK);
    }

    public static boolean isLeftClick(MouseEvent e) {
        return (e.getModifiers() == InputEvent.BUTTON1_MASK);
    }

    public static boolean isRightClick(MouseEvent e) {
        return (e.getModifiers() == InputEvent.BUTTON3_MASK);
    }

    private Util() {
    }
}
