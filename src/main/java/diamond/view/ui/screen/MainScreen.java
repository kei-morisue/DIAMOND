/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.Step;
import diamond.view.ui.screen.draw.Drawer;

/**
 * @author Kei Morisue
 *
 */
public class MainScreen extends AbstractScreen {
    private Context context;

    public MainScreen(Context context) {
        super();
        this.context = context;
        //PaintScreenAction screenAction = new PaintScreenAction(context, this);
        //        addMouseListener(screenAction);
        //        addMouseMotionListener(screenAction);
        //        addMouseWheelListener(screenAction);
    }

    @Override
    void draw(Graphics2D g2d) {
        Step currentStep = context.getCurrentStep();
        Drawer drawer = new Drawer(context.getStyleFace(),
                context.getStyleSegment());
        drawer.Draw(g2d, currentStep);
    }

}
