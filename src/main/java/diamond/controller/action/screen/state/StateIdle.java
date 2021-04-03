/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateIdle<T extends F<T>> extends AbstractScreenState<T> {
    @Deprecated
    public StateIdle(
            Context<T> context,
            AbstractScreenState<T> prevState) {
        super(context, prevState);
    }

    public StateIdle(Context<T> context) {
        super(context, null);
    }

    @Override
    protected AbstractScreenState<T> onRight() {
        return this;
    }

    @Override
    public AbstractScreenState<T> left(Ver<T> v) {
        if (isCtrl) {
            return new StateIdleV<T>(context, this, v);

        }
        return new StateIdle124<T>(context, this, v);
    }

    public AbstractScreenState<T> left(Seg<T> t) {
        if (isCtrl) {
            t.flip(context.getStep());
            return this;
        }
        return new StateIdle34<T>(context, this, t);
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        float scale = (float) screen.getScale();
        if (pointedV != null) {
            pointedV.draw(screen, g2d, scale, true);
            return;
        }
        if (pointedS != null) {
            pointedS.draw(screen, g2d, scale, true);
        }
    }

}
