/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import diamond.controller.Context;
import diamond.controller.action.screen.state.AbstractScreenState;
import diamond.controller.action.screen.state.StateIdol;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenModelAction<T extends F<T>>
        extends AbstractScreenAction<T> {
    private Context<T> context;
    protected Ver<T> v;
    protected Seg<T> s;
    protected AbstractScreenState state = new StateIdol();

    public ScreenModelAction(
            Context<T> context,
            AbstractScreen<T> screen) {
        super(screen);
        this.context = context;
    }

    private void initialize() {
        v = null;
        s = null;
    }

    @Override
    public void onAction(MouseEvent e) {
        boolean isCtrl = Util.isControlKeyPressed(e);
        boolean isLeft = Util.isLeftClick(e);
        state = state.next(isLeft, isCtrl, v, s);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //        context.getPaintAction().onRelease();//TODO
    }

    @Override
    public void mouseExited(MouseEvent e) {
        initialize();
    }

    @Override
    protected void set(double x, double y) {
        double scale = screen.getScale();
        Step<T> step = context.getStep();
        v = step.findVer(x, y, scale);
        s = step.findSeg(x, y, scale);
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (v != null) {
            v.draw((ScreenModel<T>) screen, g2d);
            state.draw(g2d);
            return;
        }
        if (s != null) {
            s.drawPointed((ScreenModel<T>) screen, g2d);
            state.draw(g2d);
            return;
        }

    }

}
