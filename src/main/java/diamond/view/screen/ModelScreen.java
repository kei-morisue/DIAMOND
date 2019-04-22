/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.paint.ScreenContext;
import diamond.model.geom.element.origami.OriModel;
import diamond.view.screen.draw.OriDrawer;
import diamond.view.screen.draw.style.ColorStyle;

/**
 * @author long_
 *
 */
public class ModelScreen extends AbstractScreen {
    private ScreenContext context;

    public ModelScreen(ScreenContext modelContext) {
        super(modelContext);
        this.context = modelContext;
        modelContext.palette.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, ColorStyle.MODEL_SCREEN_BG);
        ScreenTransform transform = context.transform;
        transform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(transform.getTransform());
        OriModel model = context.palette.getOriModel();
        OriDrawer.drawModel(g2d, model);
        OriDrawer.drawStepNo(g2d, context.palette.getStepNo() + 1);

    }
}
