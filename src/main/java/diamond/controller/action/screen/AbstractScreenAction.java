/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreenAction<T extends F<T>>
        implements MouseListener, MouseMotionListener, MouseWheelListener {
    protected AbstractScreen<T> screen;
    protected Point2D latestClickedPoint;

    protected AbstractScreenAction(AbstractScreen<T> screen) {
        this.screen = screen;
    }

    public final void mouseDragged(MouseEvent e) {
        translate(e);
        latestClickedPoint = e.getPoint();
        screen.repaint();
    }

    public void mouseMoved(MouseEvent e) {
        Point2D.Double p = screen.getLogicalPoint(e.getPoint());
        set(p.x, p.y);
    }

    protected abstract void set(double x, double y);

    public final void mouseWheelMoved(MouseWheelEvent e) {
        if (Util.isControlKeyPressed(e)) {
            rotate(e);
        } else {
            zoom(e);
        }
        screen.repaint();
    }

    protected void zoom(MouseWheelEvent e) {
        screen.zoom(Math.pow(1.5, -e.getWheelRotation()));
    }

    protected void translate(MouseEvent e) {
        if (e.isControlDown()) {
            return;
        }
        Point2D p0 = latestClickedPoint;
        screen.translate(
                e.getX() - p0.getX(),
                e.getY() - p0.getY());
        latestClickedPoint = e.getPoint();
    }

    protected void rotate(MouseWheelEvent e) {
        double theta = thetaAmount(e);
        screen.rotate(theta);
    }

    protected double thetaAmount(MouseWheelEvent e) {
        double moved = e.getWheelRotation();
        return Math.PI / 8 * ((moved) % 8);
    }

    @Override
    public final void mouseClicked(MouseEvent e) {
    }

    @Override
    public final void mouseEntered(MouseEvent e) {
        screen.requestFocus();
    }

    public abstract void onAction(MouseEvent e);

    public abstract void draw(Graphics2D g2d);

    @Override
    public final void mousePressed(MouseEvent e) {
        latestClickedPoint = e.getPoint();
        onAction(e);
    }

}
