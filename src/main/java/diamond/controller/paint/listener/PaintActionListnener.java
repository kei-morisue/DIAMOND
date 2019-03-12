/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.util.MouseUtility;

/**
 * @author long_
 *
 */
public class PaintActionListnener
        implements MouseListener, MouseMotionListener {
    private PaintContext paintContext;

    public PaintActionListnener(PaintContext paintContext) {
        this.paintContext = paintContext;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paintContext.currentLogicalMousePoint = MouseUtility.getLogicalPoint(
                paintContext.coordinateTransform.getTransform(),
                e.getPoint());
        paintContext.paintAction.onMove(paintContext);
        e.getComponent().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MouseUtility.isLeftClick(e)) {
            paintContext.paintAction.onLeftClick(paintContext);
        }
        if (MouseUtility.isRightClick(e)) {
            paintContext.paintAction.onRightClick(paintContext);
        }
        e.getComponent().repaint();
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        paintContext.currentLogicalMousePoint = MouseUtility.getLogicalPoint(
                paintContext.coordinateTransform.getTransform(),
                e.getPoint());
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
