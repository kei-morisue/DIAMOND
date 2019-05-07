/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.paint.ModelContext;
import diamond.model.geom.element.origami.OriModel;
import diamond.view.screen.draw.OriModelDrawer;
import diamond.view.screen.draw.StringDrawer;
import diamond.view.screen.draw.style.FontStyle;
import diamond.view.screen.draw.style.color.Ui;

/**
 * @author long_
 *
 */
public class ModelScreen extends AbstractScreen {
    private ModelContext context;

    public ModelScreen(ModelContext modelContext) {
        super(modelContext);
        this.context = modelContext;
        modelContext.palette.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, Ui.MODEL_SCREEN_BG);
        ScreenTransform transform = context.transform;
        transform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(transform.getTransform());
        OriModel model = context.palette.getOriModel();
        OriModelDrawer.drawModel(g2d, model);
        StringDrawer.drawStepNo(
                g2d,
                context.palette.getStepNo() + 1,
                FontStyle.MODEL_STEP_NO);
    }
}
