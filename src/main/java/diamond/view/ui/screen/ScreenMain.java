/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import diamond.controller.Context;
import diamond.controller.action.ScreenActionPaint;
import diamond.model.cyborg.step.Step;
import diamond.view.ui.screen.draw.CyborgDrawer;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public class ScreenMain extends AbstractScreen implements Observer {
    private Context context;

    protected ScreenMain() {
    }

    public ScreenMain(Context context) {
        super();
        this.context = context;
        ScreenActionPaint screenAction = new ScreenActionPaint(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
        context.addObserver(this);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_MAIN_SCREEN;
    }

    @Override
    protected void draw(Graphics2D g2d) {
        CyborgDrawer drawer = new CyborgDrawer(g2d, context);
        Step step = context.getDiagram().getStep();
        drawer.draw(g2d, step);
        context.getPaintAction().onDraw(g2d);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}
