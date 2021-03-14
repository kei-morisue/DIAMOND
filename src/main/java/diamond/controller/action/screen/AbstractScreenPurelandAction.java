/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.event.MouseEvent;

import diamond.controller.Context;
import diamond.controller.action.screen.state.AbstractScreenState;
import diamond.controller.action.screen.state.StateIdle;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreenPurelandAction<T extends F<T>>
        extends AbstractScreenAction<T> {
    protected AbstractScreenState<T> state;

    protected AbstractScreenPurelandAction(
            Context<T> context,
            AbstractScreen<T> screen) {
        super(screen);
        this.state = new StateIdle<T>(context);
        screen.addKeyListener(state);
    }

    @Override
    public void onAction(MouseEvent e) {
        boolean isCtrl = Util.isControlKeyPressed(e);
        boolean isLeft = Util.isLeftClick(e);
        screen.removeKeyListener(state);
        state = state.next(isLeft, isCtrl);
        screen.addKeyListener(state);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        state.initialize();
    }

}
