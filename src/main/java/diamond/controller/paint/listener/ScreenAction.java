/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import diamond.controller.paint.ScreenContext;
import diamond.controller.paint.util.MouseUtility;

/**
 * @author long_
 *
 */
public class ScreenAction
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    private ScreenContext screenContext;
    public Point2D latestClickedPoint;

    public ScreenAction(ScreenContext screenContext) {
        this.screenContext = screenContext;
    }

    private void zoom(MouseWheelEvent e) {
        double scale_ = Math.pow(1.5, -e.getWheelRotation());
        screenContext.transform.zoom(scale_);
        e.getComponent().repaint();
    }

    private void translate(MouseEvent e) {
        double scale = screenContext.transform.getScale();
        Point2D p0 = latestClickedPoint;
        double x = (e.getX() - p0.getX()) / scale;
        double y = (e.getY() - p0.getY()) / scale;
        screenContext.transform.translate(x, y);
        latestClickedPoint = e.getPoint();
        e.getComponent().repaint();
    }

    private void rotate(MouseWheelEvent e) {
        double moved = e.getWheelRotation();
        screenContext.transform
                .rotate(Math.PI / 8 * ((moved) % 8));
        latestClickedPoint = e.getPoint();
        e.getComponent().repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (MouseUtility.isControlKeyPressed(e)) {
            rotate(e);
        } else {
            zoom(e);
        }
        e.getComponent().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        translate(e);
        latestClickedPoint = e.getPoint();
        e.getComponent().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.getComponent().repaint();
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        latestClickedPoint = e.getPoint();
        e.getComponent().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
