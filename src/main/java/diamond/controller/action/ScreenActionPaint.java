/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;

import diamond.controller.Context;
import diamond.view.ui.screen.MainScreen;

/**
 * @author Kei Morisue
 *
 */
public class ScreenActionPaint extends AbstractScreenAction {
    private Context context;

    public ScreenActionPaint(Context context, MainScreen screen) {
        super(screen);
        this.context = context;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //        context.setMousePoint(MouseUtility.getLogicalPoint(
        //                screen.getTransform(),
        //                e.getPoint()));
        //        context.getPaintAction().onMove(context);
        e.getComponent().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //        if (MouseUtility.isLeftClick(e)) {
        //            context.getPaintAction().onLeftClick(context);
        //        }
        //        if (MouseUtility.isRightClick(e)) {
        //            context.getPaintAction().onRightClick(context);
        //        }
        e.getComponent().repaint();
        return;
    }

}
