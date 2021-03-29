/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenModelAction<T extends F<T>>
        extends AbstractScreenPurelandAction<T> {

    public ScreenModelAction(
            Context<T> context,
            ScreenModel<T> screen) {
        super(context, screen);
    }

    @Override
    protected void set(double x, double y) {
        double scale = screen.getScale();
        Step<T> step = state.getContext().getStep();
        Ver<T> v = step.findVer(x, y, scale);
        if (v != null) {
            state.setPointedS(null);
            state.setPointedV(v);
            return;
        }
        D1<T> s = step.findEdge(x, y, scale);
        if (s == null) {
            s = step.findCrease(x, y, scale);//TODO find seg & link
        }
        if (s != null) {
            Ver<T> node = s.findNode(x, y, scale);
            if (node != null) {
                s = null;
                v = node;
            }
        }
        state.setPointedS(s);
        state.setPointedV(v);
    }

    @Override
    public void draw(Graphics2D g2d) {
        ScreenModel<T> screenModel = (ScreenModel<T>) screen;
        state.drawModel(screenModel, g2d);
        g2d.drawString(state.getClass().getName(), 0, 0);//TODO
    }

}
