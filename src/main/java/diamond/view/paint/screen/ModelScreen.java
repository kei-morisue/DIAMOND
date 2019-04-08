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
import diamond.model.geom.element.origami.OriModel;
import diamond.view.paint.screen.draw.OriDrawer;
import diamond.view.paint.screen.draw.style.ColorStyle;

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
        modelContext.palette.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, ColorStyle.MODEL_SCREEN_BG);
        transform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(transform.getTransform());
        OriModel model = modelContext.palette.getOriModel();
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
