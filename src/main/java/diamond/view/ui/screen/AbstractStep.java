/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import diamond.controller.action.StepScreenAction;
import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractStep extends AbstractScreen {
    protected Cp cp;
    protected int x = 0;
    protected int y = 0;

    public AbstractStep() {
    }

    public AbstractStep(Cp cp) {
        this.cp = cp;
        this.transform = cp.getTransform();
        this.transform.zoom(0.5);
        StepScreenAction screenAction = new StepScreenAction(this, cp);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
        setBackground(Color.white);
    }

    abstract protected void draw(Graphics2D g2d);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        this.transform.focus(getWidth(), getHeight());
        AffineTransform transform2 = g2d.getTransform();
        x = (int) transform2.getTranslateX();
        y = (int) transform2.getTranslateY();
        transform2.concatenate(transform);
        g2d.setTransform(transform2);
        draw(g2d);
        g2d.dispose();
    }
}
