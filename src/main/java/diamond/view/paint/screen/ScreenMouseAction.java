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

import diamond.mouse.MouseUtility;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;

/**
 * @author long_
 *
 */
public class ScreenMouseAction
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    private ScreenAxisTransform screenAxisTransform;

    private Point2D.Double currentMousePointLogic = new Point2D.Double();
    private Point2D preMousePoint;

    public ScreenMouseAction(ScreenAxisTransform screenScaling) {
        this.screenAxisTransform = screenScaling;
    }

    private void zoomAction(MouseWheelEvent e) {
        double scale_ = Math.pow(1.5, -e.getWheelRotation());
        screenAxisTransform.zoom(scale_);
        PaintContext.getInstance().scale = screenAxisTransform.getScale();
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
        PaintContext.getInstance().setLogicalMousePoint(currentMousePointLogic);
        if (PaintConfig.mouseAction == null) {
            return;
        }
        PaintConfig.mouseAction.onMove(PaintContext.getInstance(),
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
        PaintContext.getInstance().setLogicalMousePoint(MouseUtility
                .getLogicalPoint(screenAxisTransform.getTransform(),
                        e.getPoint()));
        PaintConfig.getMouseAction().onDrag(PaintContext.getInstance(),
                screenAxisTransform.getTransform(),
                MouseUtility.isControlKeyPressed(e));
        e.getComponent().repaint();
        return;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (PaintConfig.mouseAction == null) {
            return;
        }
        PaintContext paintContext = PaintContext.getInstance();

        if (javax.swing.SwingUtilities.isRightMouseButton(e)) {
            PaintConfig.mouseAction.onRightClick(
                    paintContext, screenAxisTransform.getTransform(),
                    MouseUtility.isControlKeyPressed(e));
            return;
        }

        PaintConfig.mouseAction = PaintConfig.mouseAction.onLeftClick(
                paintContext, screenAxisTransform.getTransform(),
                MouseUtility.isControlKeyPressed(e));
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (PaintConfig.mouseAction == null) {
            return;
        }
        PaintConfig.mouseAction.onPress(PaintContext.getInstance(),
                screenAxisTransform.getTransform(),
                MouseUtility.isControlKeyPressed(e));
        preMousePoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (PaintConfig.mouseAction != null) {
            PaintConfig.mouseAction.onRelease(PaintContext.getInstance(),
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
