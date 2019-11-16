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

import diamond.model.AffineManager;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class ScreenAction
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    private AbstractScreen screen;
    private Point2D latestClickedPoint;

    public ScreenAction(AbstractScreen screen) {
        this.screen = screen;
    }

    private void zoom(MouseWheelEvent e) {
        double scale = Math.pow(1.5, -e.getWheelRotation());
        AffineManager.zoom(screen.getTransform(), scale);
        e.getComponent().repaint();
    }

    private void translate(MouseEvent e) {
        double scale = AffineManager.getScale(screen.getTransform());
        Point2D p0 = latestClickedPoint;
        double x = (e.getX() - p0.getX()) / scale;
        double y = (e.getY() - p0.getY()) / scale;
        AffineManager.translate(screen.getTransform(), x, y);
        latestClickedPoint = e.getPoint();
        e.getComponent().repaint();
    }

    private void rotate(MouseWheelEvent e) {
        double moved = e.getWheelRotation();
        double theta = Math.PI / 8 * ((moved) % 8);
        AffineManager.rotate(screen.getTransform(), theta);
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
