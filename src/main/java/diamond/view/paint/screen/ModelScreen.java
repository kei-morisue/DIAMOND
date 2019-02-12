/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.fold.Folder;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.view.paint.screen.draw.OriDrawer;
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
        drawBG(g2d, ColorStyle.MODEL_SCREEN_BG);
        paintContext.coordinateTransform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(paintContext.coordinateTransform.getTransform());
        OriModel model = new OriModel(paintContext.getCP());
        Folder.fold(model);
        OriDrawer.drawModel(g2d, model);
    }
}
