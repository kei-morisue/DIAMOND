/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractStep extends JPanel {
    protected Cp cp;
    protected int x = 0;
    protected int y = 0;

    public AbstractStep() {
    }

    public AbstractStep(Cp cp) {
        this.cp = cp;
        setBackground(Color.white);
    }

    abstract protected void draw(Graphics2D g2d);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        ScreenTransform transform = new ScreenTransform(cp.getTransform());
        transform.focus(getWidth(), getHeight());
        transform.zoom(0.3);
        AffineTransform transform2 = g2d.getTransform();
        x = (int) transform2.getTranslateX();
        y = (int) transform2.getTranslateY();
        transform2.concatenate(transform);
        g2d.setTransform(transform2);
        draw(g2d);
        g2d.dispose();
    }
}
