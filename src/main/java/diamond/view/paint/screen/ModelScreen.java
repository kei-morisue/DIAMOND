/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.fold.Folder;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.model.palette.cp.CreasePattern;
import diamond.view.paint.screen.draw.OriDrawer;
import diamond.view.resource.color.ColorStyle;
import diamond.view.resource.graphic.LineStrokeSetting;

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

        CreasePattern creasePattern = paintContext.getCP();
        OriModel oriModel = new OriModel(creasePattern);
        Folder.fold(oriModel);
        GeneralPath path = new GeneralPath();

        for (OriFace face : oriModel.getFaces()) {
            path.reset();
            for (OriHalfEdge he : face.halfEdges) {
                OriDrawer.drawLine(
                        g2d,
                        he,
                        Color.green,
                        LineStrokeSetting.STROKE_VALLEY);
            }
        }
    }
}
