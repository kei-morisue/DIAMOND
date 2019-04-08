/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

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

    public ModelScreen(ModelContext modelContext) {
        super(modelContext);
        this.modelContext = modelContext;
        modelContext.palette.addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, ColorStyle.MODEL_SCREEN_BG);
        ScreenTransform transform = modelContext.transform;
        transform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(transform.getTransform());
        OriModel model = modelContext.palette.getOriModel();
        OriDrawer.drawModel(g2d, model);
        OriDrawer.drawStepNo(g2d, modelContext.palette.getStepNo() + 1, 100);

    }
}
