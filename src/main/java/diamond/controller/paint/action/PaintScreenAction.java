/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.util.MouseUtility;

/**
 * @author long_
 *
 */
public class PaintScreenAction
        implements MouseListener, MouseMotionListener {
    private PaintContext paintContext;

    public PaintScreenAction(PaintContext paintContext) {
        this.paintContext = paintContext;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paintContext.setCurrentLogicalMousePoint(MouseUtility.getLogicalPoint(
                paintContext.getTransform().getTransform(),
                e.getPoint()));
        paintContext.getPaintAction().onMove(paintContext);
        e.getComponent().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MouseUtility.isLeftClick(e)) {
            paintContext.getPaintAction().onLeftClick(paintContext);
        }
        if (MouseUtility.isRightClick(e)) {
            paintContext.getPaintAction().onRightClick(paintContext);
        }
        e.getComponent().repaint();
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintContext.setCurrentLogicalMousePoint(MouseUtility.getLogicalPoint(
                paintContext.getTransform().getTransform(),
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
