/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.mouse;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * @author Kei Morisue
 *
 */
public class MouseUtility {
	public static Point2D.Double getLogicalPoint(AffineTransform affine, Point mousePoint) {
		Point2D.Double logicalPoint = new Point2D.Double();
		try {
			affine.inverseTransform(mousePoint, logicalPoint);
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		return logicalPoint;
	}

	public static boolean isControlKeyPressed(MouseEvent e) {
		return ((e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK);
	}

	public static boolean isLeftClick(MouseEvent e) {
		return (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK);
	}

	public static boolean isRightClick(MouseEvent e) {
		return (e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK);
	}

	private MouseUtility() {
	}
}