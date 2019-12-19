/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;

import diamond.controller.Context;
import diamond.controller.MouseUtility;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreenAction extends AbstractScreenAction {
    private Context context;

    public PaintScreenAction(Context context, PaintScreen screen) {
        super(screen);
        this.context = context;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        context.setMousePoint(MouseUtility.getLogicalPoint(
                context.getPaintScreen().getTransform(),
                e.getPoint()));
        context.getPaintAction().onMove(context);
        e.getComponent().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MouseUtility.isLeftClick(e)) {
            context.getPaintAction().onLeftClick(context);
        }
        if (MouseUtility.isRightClick(e)) {
            context.getPaintAction().onRightClick(context);
        }
        e.getComponent().repaint();
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        context.getPointer().clear();
    }
}
