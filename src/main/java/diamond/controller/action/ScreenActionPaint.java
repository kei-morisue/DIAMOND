/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import diamond.controller.Context;
import diamond.controller.mouse.Util;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenActionPaint extends AbstractScreenAction {
    private Context context;

    public ScreenActionPaint(Context context, AbstractScreen screen) {
        super(screen);
        this.context = context;
    }

    @Override
    public final void mouseWheelMoved(MouseWheelEvent e) {
        if (Util.isControlKeyPressed(e)) {
            rotate(e);
        } else {
            zoom(e);
        }
        screen.repaint();
    }

    @Override
    public final void mouseDragged(MouseEvent e) {
        translate(e);
        latestClickedPoint = e.getPoint();
        screen.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //        Vertex v = Util.getLogicalPoint(
        //                screen.getTransform(),
        //                e.getPoint());
        //        context.setPointed(v);
        //        context.getPaintAction().onMove();
    }

    @Override
    public void onAction(MouseEvent e) {
        boolean isCtrl = Util.isControlKeyPressed(e);
        boolean isLeft = Util.isLeftClick(e);
        //        AbstractPaintAction paintAction = context.getPaintAction();
        //        paintAction.onPress(isLeft, isCtrl);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //        context.getPaintAction().onRelease();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //        context.getPointer().initialize();//TODO
    }

}
