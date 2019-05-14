/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public abstract class AbstractStep extends JPanel {
    private Diagram diagram;
    protected int x = 0;
    protected int y = 0;

    public AbstractStep(Diagram diagram, int stepNo) {
        super();
        this.diagram = diagram;
        setBackground(Color.white);
    }

    abstract protected void draw(Graphics2D g2d);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        ScreenTransform transform = new ScreenTransform(diagram.getTransform());
        transform.focus(getWidth(), getHeight());
        transform.zoom(0.3);
        AffineTransform transform2 = g2d.getTransform();
        x = (int) transform2.getTranslateX();
        y = (int) transform2.getTranslateY();
        transform2.concatenate(transform.getTransform());
        g2d.setTransform(transform2);
        draw(g2d);
        g2d.dispose();
    }
}
