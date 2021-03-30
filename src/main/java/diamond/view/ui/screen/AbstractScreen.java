/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Point2D;
import java.util.Observer;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreen<T extends F<T>> extends CyborgScreen
        implements ComponentListener, Observer {
    protected TransformScreen transform = new TransformScreen();
    protected Context<T> context;

    @Deprecated
    public AbstractScreen() {
    }

    public AbstractScreen(Context<T> context) {
        addComponentListener(this);
        this.context = context;
        context.addObserver(this);
        setFocusable(true);
    }

    public Point2D.Double getLogicalPoint(Point2D mousePoint) {
        Point2D.Double logicalPoint = new Point2D.Double();
        transform.getInverse().transform(mousePoint, logicalPoint);
        return logicalPoint;
    }

    public Step<T> getStep() {
        return context.getStep();
    }

    public double getScale() {
        return transform.getZoom();
    }

    public void zoom(double z) {
        transform.zoom(z);
        repaint();
    }

    public void translate(double dx, double dy) {
        double scale = transform.getZoom();
        transform.shift(dx / scale, dy / scale);
        repaint();
    }

    public void rotate(double r) {
        transform.rotate(r);
        repaint();
    }

    protected abstract Color getBGColor();

    protected abstract void drawStep(Graphics2D g2d);

    protected abstract void drawPointed(Graphics2D g2d);

    protected void drawBackGround(Graphics2D g2d) {
        g2d.setColor(getBGColor());
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d);
        transform.resize(getWidth(), getHeight());
        g2d.setTransform(transform.getTransform());
        drawStep(g2d);
        drawPointed(g2d);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
        repaint();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}
