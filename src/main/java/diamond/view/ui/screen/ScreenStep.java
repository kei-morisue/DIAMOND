/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;

import diamond.controller.Context;
import diamond.controller.action.ScreenActionPaint;
import diamond.model.cyborg.diagram.step.Step;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenStep extends AbstractScreen {

    public ScreenStep(Context context) {
        super(context);
        ScreenActionPaint screenAction = new ScreenActionPaint(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_STEP_SCREEN;
    }

    @Override
    protected void draw(Graphics2D g2d) {
        Step step = diagram().getStep();
        step.draw(g2d, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void drawPointed(Graphics2D g2d) {
    }

}
