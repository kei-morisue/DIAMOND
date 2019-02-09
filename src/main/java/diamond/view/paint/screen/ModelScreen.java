/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.color.ColorStyle;

/**
 * @author long_
 *
 */
public class ModelScreen extends AbstractScreen {
    private PaintContext paintContext;

    public ModelScreen(PaintContext paintContext) {
        super(paintContext);
        this.paintContext = paintContext;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        setBackground(ColorStyle.FRAME_BG);
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
        paintContext.coordinateTransform.ResizeWindow(width, height);
        g2d.setTransform(paintContext.coordinateTransform.getTransform());

    }
}
