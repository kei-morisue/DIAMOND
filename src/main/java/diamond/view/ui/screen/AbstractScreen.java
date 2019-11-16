/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import diamond.controller.ScreenAction;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreen extends JPanel
        implements ComponentListener {
    public AbstractScreen() {
        addComponentListener(this);
        ScreenAction screenAction = new ScreenAction(this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
    }

    public AffineTransform getTransform() {
        return ((Graphics2D) getGraphics()).getTransform();
    }

    protected void drawBackGround(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
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
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
