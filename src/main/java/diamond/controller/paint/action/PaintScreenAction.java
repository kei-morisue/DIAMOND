/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.util.MouseUtility;

/**
 * @author long_
 *
 */
public class PaintScreenAction
        implements MouseListener, MouseMotionListener {
    private Context context;
    private PaintScreenContext paintScreenContext;

    public PaintScreenAction(Context context) {
        this.context = context;
        this.paintScreenContext = context.getPaintScreenContext();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paintScreenContext
                .setCurrentLogicalMousePoint(MouseUtility.getLogicalPoint(
                        paintScreenContext.getTransform().getTransform(),
                        e.getPoint()));
        paintScreenContext.getPaintAction().onMove(context);
        e.getComponent().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MouseUtility.isLeftClick(e)) {
            paintScreenContext.getPaintAction().onLeftClick(context);
        }
        if (MouseUtility.isRightClick(e)) {
            paintScreenContext.getPaintAction().onRightClick(context);
        }
        e.getComponent().repaint();
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintScreenContext
                .setCurrentLogicalMousePoint(MouseUtility.getLogicalPoint(
                        paintScreenContext.getTransform().getTransform(),
                        e.getPoint()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
