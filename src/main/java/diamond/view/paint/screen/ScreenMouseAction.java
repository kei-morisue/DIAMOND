/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen;

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
    private CoodinateTransform coodinateTransform;
    private PaintContext paintContext;

    public ScreenMouseAction(CoodinateTransform screenScaling,
            PaintContext paintContext) {
        this.coodinateTransform = screenScaling;
        this.paintContext = paintContext;
    }

    private void zoom(MouseWheelEvent e) {
        double scale_ = Math.pow(1.5, -e.getWheelRotation());
        coodinateTransform.zoom(scale_);
        e.getComponent().repaint();
    }

    private void translate(MouseEvent e) {
        double scale = coodinateTransform.getScale();
        Point2D p0 = paintContext.clickedLatestPoint;
        double x = (e.getX() - p0.getX()) / scale;
        double y = (e.getY() - p0.getY()) / scale;
        coodinateTransform.translate(x, y);
        paintContext.clickedLatestPoint = e.getPoint();
        e.getComponent().repaint();
    }

    private void rotate(MouseWheelEvent e) {
        double moved = e.getWheelRotation();
        coodinateTransform
                .rotate(Math.PI / 8 * ((moved) % 8));
        paintContext.clickedLatestPoint = e.getPoint();
        e.getComponent().repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (MouseUtility.isControlKeyPressed(e)) {
            rotate(e);
        } else {
            zoom(e);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paintContext.currentlogicalMousePoint = MouseUtility.getLogicalPoint(
                coodinateTransform.getTransform(),
                e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        translate(e);
        paintContext.clickedLatestPoint = e.getPoint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintContext.clickedLatestPoint = e.getPoint();
        e.getComponent().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        paintContext.bEffective = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        paintContext.bEffective = false;
    }

}
