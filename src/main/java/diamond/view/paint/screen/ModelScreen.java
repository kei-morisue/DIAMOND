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
        g2d.setColor(ColorStyle.FRAME_BG);
        int width = getWidth();
        int height = getHeight();
        g2d.fillRect(0, 0, width, height);
        paintContext.coordinateTransform.ResizeWindow(width, height);
        g2d.setTransform(paintContext.coordinateTransform.getTransform());
        //        OriModel model = new OriModel(paintContext.getCP());
        //        Folder.fold(model);
        //        for (OriFace face : model.getFaces()) {
        //            GeneralPath path = null;
        //            for (OriHalfEdge he : face.getHalfEdges()) {
        //                Point2D ptDst = new Point2D.Double();
        //                Vector2d v = he.getSv();
        //                Point2D ptSrc = new Point2D.Double(v.x, v.y);
        //                face.getTransform().transform(ptSrc, ptDst);
        //                if (path == null) {
        //                    path = new GeneralPath();
        //                    path.moveTo(ptDst.getX(), ptDst.getY());
        //                } else {
        //                    path.lineTo(ptDst.getX(), ptDst.getY());
        //                }
        //            }
        //            path.closePath();
        //            g2d.setColor(new Color(255, 255, 255, 100));
        //            g2d.fill(path);
        //        }
    }
}
