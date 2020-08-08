/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import diamond.controller.Context;
import diamond.controller.mouse.Util;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.view.ui.screen.ScreenMain;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenActionPaint extends AbstractScreenAction {
    private Context context;

    public ScreenActionPaint(Context context, ScreenMain screen) {
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
        Vertex v = Util.getLogicalPoint(
                screen.getTransform(),
                e.getPoint());
        context.setPointed(v);
        context.getPaintAction().onMove();
        context.notifyObservers();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Util.isLeftClick(e)) {
            if (Util.isControlKeyPressed(e)) {
                context.getPaintAction().onLeftCtrlClick();
            }
            context.getPaintAction().onLeftClick();
        }
        if (Util.isRightClick(e)) {
            if (Util.isControlKeyPressed(e)) {
                context.getPaintAction().onRightCtrlClick();
            }
            context.getPaintAction().onRightClick();
        }
        context.notifyObservers();
    }

}
