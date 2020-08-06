/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;

import diamond.controller.Context;
import diamond.controller.mouse.Util;
import diamond.view.ui.screen.ScreenMain;

/**
 * @author Kei Morisue
 *
 */
public class ScreenActionPaint extends AbstractScreenAction {
    private Context context;

    public ScreenActionPaint(Context context, ScreenMain screen) {
        super(screen);
        this.context = context;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        context.setPointed(Util.getLogicalPoint(
                screen.getTransform(),
                e.getPoint()));
        context.getPaintAction().onMove(context);
        context.notifyObservers();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Util.isLeftClick(e)) {
            context.getPaintAction().onLeftClick(context);
        }
        if (Util.isRightClick(e)) {
            context.getPaintAction().onRightClick(context);
        }
        context.notifyObservers();
    }

}
