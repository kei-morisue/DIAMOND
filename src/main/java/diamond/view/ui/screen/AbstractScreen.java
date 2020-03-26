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

import javax.swing.JPanel;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreen extends JPanel
        implements ComponentListener {
    protected ScreenTransform transform = new ScreenTransform();

    public AbstractScreen() {
        addComponentListener(this);
    }

    public ScreenTransform getTransform() {
        return transform;
    };

    public void setTransform(ScreenTransform screenTransform) {
        this.transform = screenTransform;
    };

    protected void drawBackGround(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
    }

    abstract void draw(Graphics2D g2d);

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.transform.resize(getWidth(), getHeight());
        g2d.setTransform(transform);
        draw(g2d);
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
