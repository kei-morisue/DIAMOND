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

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.util.MouseUtility;

/**
 * @author long_
 *
 */
public class PaintScreenCoordinateListnener
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    private PaintContext paintContext;
    public Point2D latestClickedPoint;

    public PaintScreenCoordinateListnener(PaintContext paintContext) {
        this.paintContext = paintContext;
    }

    private void zoom(MouseWheelEvent e) {
        double scale_ = Math.pow(1.5, -e.getWheelRotation());
        paintContext.coordinateTransform.zoom(scale_);
        e.getComponent().repaint();
    }

    private void translate(MouseEvent e) {
        double scale = paintContext.coordinateTransform.getScale();
        Point2D p0 = latestClickedPoint;
        double x = (e.getX() - p0.getX()) / scale;
        double y = (e.getY() - p0.getY()) / scale;
        paintContext.coordinateTransform.translate(x, y);
        latestClickedPoint = e.getPoint();
        e.getComponent().repaint();
    }

    private void rotate(MouseWheelEvent e) {
        double moved = e.getWheelRotation();
        paintContext.coordinateTransform
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
