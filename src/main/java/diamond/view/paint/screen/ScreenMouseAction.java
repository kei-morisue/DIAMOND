/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import diamond.action.MouseUtility;
import diamond.view.paint.PaintContext;

/**
 * @author long_
 *
 */
public class ScreenMouseAction
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    private ScreenAxisTransform screenAxisTransform;

    private Point2D.Double currentMousePointLogic = new Point2D.Double();
    private Point2D preMousePoint;

    private PaintContext paintContext = new PaintContext();

    public ScreenMouseAction(ScreenAxisTransform screenScaling) {
        this.screenAxisTransform = screenScaling;
    }

    private void zoomAction(MouseWheelEvent e) {
        double scale_ = Math.pow(1.5, -e.getWheelRotation());
        screenAxisTransform.zoom(scale_);
        e.getComponent().repaint();
    }

    private void translateAction(MouseEvent e) {
        double scale = screenAxisTransform.getScale();
        double x = (e.getX() - preMousePoint.getX()) / scale;
        double y = (e.getY() - preMousePoint.getY()) / scale;
        screenAxisTransform.translate(x, y);
        preMousePoint = e.getPoint();
        e.getComponent().repaint();
    }

    private void rotateAction(MouseEvent e) {
        double moved = e.getX() - preMousePoint.getX();
        screenAxisTransform
                .rotate(Math.PI / 8 * ((moved) % 8));
        preMousePoint = e.getPoint();
        e.getComponent().repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomAction(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        currentMousePointLogic = MouseUtility.getLogicalPoint(
                screenAxisTransform.getTransform(),
                e.getPoint());
        paintContext.setLogicalMousePoint(currentMousePointLogic);
        if (paintContext.getMouseAction() == null) {
            return;
        }
        paintContext.getMouseAction().onMove(paintContext,
                screenAxisTransform.getTransform(),
                MouseUtility.isControlKeyPressed(e));
        e.getComponent().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0 &&
                (e.getModifiersEx()
                        & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {
            rotateAction(e);
            return;
        }
        if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
            translateAction(e);
            return;
        }
        paintContext.setLogicalMousePoint(MouseUtility
                .getLogicalPoint(screenAxisTransform.getTransform(),
                        e.getPoint()));
        paintContext.getMouseAction().onDrag(paintContext,
                screenAxisTransform.getTransform(),
                MouseUtility.isControlKeyPressed(e));
        e.getComponent().repaint();
        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (paintContext.getMouseAction() == null) {
            return;
        }
        if (javax.swing.SwingUtilities.isRightMouseButton(e)) {
            paintContext.getMouseAction().onRightClick(
                    paintContext, screenAxisTransform.getTransform(),
                    MouseUtility.isControlKeyPressed(e));
            return;
        }

        paintContext.setMouseAction(paintContext.getMouseAction().onLeftClick(
                paintContext, screenAxisTransform.getTransform(),
                MouseUtility.isControlKeyPressed(e)));
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paintContext.getMouseAction() == null) {
            return;
        }
        paintContext.getMouseAction().onPress(paintContext,
                screenAxisTransform.getTransform(),
                MouseUtility.isControlKeyPressed(e));
        preMousePoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paintContext.getMouseAction() != null) {
            paintContext.getMouseAction().onRelease(paintContext,
                    screenAxisTransform.getTransform(),
                    MouseUtility.isControlKeyPressed(e));
        }
        e.getComponent().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
