package diamond.action;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * Utility Module for mouse
 * @author Koji
 *
 */
public class MouseUtility {
    public static Point2D.Double getLogicalPoint(AffineTransform affine,
            Point p) {
        Point2D.Double logicalPoint = new Point2D.Double();
        try {
            affine.inverseTransform(p, logicalPoint);
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }

        return logicalPoint;
    }

    public static boolean isControlKeyPressed(MouseEvent e) {
        return ((e.getModifiersEx()
                & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK);
    }

    public static boolean isLeftClick(MouseEvent e) {
        return ((e.getModifiersEx()
                & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK);
    }

    public static boolean isRightClick(MouseEvent e) {
        return ((e.getModifiersEx()
                & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK);
    }

    private MouseUtility() {
    }

}
