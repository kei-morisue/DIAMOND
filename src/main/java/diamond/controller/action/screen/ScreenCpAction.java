/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenCp;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenCpAction<T extends F<T>>
        extends AbstractScreenPurelandAction<T> {

    public ScreenCpAction(
            Context<T> context,
            ScreenCp<T> screen) {
        super(context, screen);
    }

    @Override
    protected void set(double x, double y) {
        double scale = screen.getScale();
        Step<T> step = state.getContext().getStep();
        //        v = step.findVer(x, y, scale);
        //        s = step.findLink(x, y, scale);
        //        if (s == null) {
        //            s = step.findSeg(x, y, scale);
        //        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        //        if (v != null) {
        //            v.draw((ScreenModel<T>) screen, g2d);
        //            state.draw(g2d);
        //            return;
        //        }
        //        if (s != null) {
        //            s.drawPointed((ScreenModel<T>) screen, g2d);
        //            state.draw(g2d);
        //            return;
        //        }

    }

}
