/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import diamond.controller.paint.ModelContext;
import diamond.model.geom.element.fold.FoldPolicy;
import diamond.model.geom.element.fold.Folder;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.view.paint.screen.draw.ColorStyle;
import diamond.view.paint.screen.draw.OriDrawer;

/**
 * @author long_
 *
 */
public class ModelScreen extends AbstractScreen {
    private ModelContext modelContext;
    private CoodinateTransform transform;

    public ModelScreen(ModelContext modelContext) {
        super(modelContext);
        this.modelContext = modelContext;
        this.transform = modelContext.transform;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, ColorStyle.MODEL_SCREEN_BG);
        transform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(transform.getTransform());
        OriModel model = modelContext.palette.getOriModel();
        Folder.fold(model, new FoldPolicy());
        OriDrawer.drawModel(g2d, model);
        if (model.getClipper() != null) {
            Clip(g2d);
        }
        OriDrawer.drawStepNo(g2d, modelContext.palette.getStepNo() + 1, 100);

    }

    private void Clip(Graphics2D g2d) {
        int w = getWidth();
        int h = getHeight();
        int r = getHeight();
        Rectangle rectangle = new Rectangle(0, 0, w, h);
        Ellipse2D clippingCircle = new Ellipse2D.Double(w / 2 - r / 2, 0, r, r);
        Area bg = new Area(rectangle);
        Area clipped = new Area(clippingCircle);
        bg.subtract(clipped);
        AffineTransform transform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.setColor(ColorStyle.MODEL_SCREEN_BG);
        g2d.fill(bg);
        g2d.setTransform(transform);

    }
}
