/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreenAction
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    private AbstractScreen screen;
    private ScreenTransform transform;
    private Point2D latestClickedPoint;

    public AbstractScreenAction(AbstractScreen screen) {
        this.screen = screen;
        this.transform = screen.getTransform();
    }

    private void zoom(MouseWheelEvent e) {
        double zoom = Math.pow(1.5, -e.getWheelRotation());
        transform.zoom(zoom);
        screen.repaint();
    }

    private void translate(MouseEvent e) {
        double scale = transform.getScale();
        Point2D p0 = latestClickedPoint;
        double x = (e.getX() - p0.getX()) / scale;
        double y = (e.getY() - p0.getY()) / scale;
        transform.shift(x, y);
        latestClickedPoint = e.getPoint();
        screen.repaint();
    }

    private void rotate(MouseWheelEvent e) {
        double moved = e.getWheelRotation();
        double theta = Math.PI / 8 * ((moved) % 8);
        transform.rotate(theta);
        screen.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (MouseUtility.isControlKeyPressed(e)) {
            rotate(e);
        } else {
            zoom(e);
        }
        screen.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        translate(e);
        latestClickedPoint = e.getPoint();
        screen.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
