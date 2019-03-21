/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.paint.ScreenContext;
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
    private ScreenContext screenContext;

    public ModelScreen(ScreenContext screenContext) {
        super(screenContext);
        this.screenContext = screenContext;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, ColorStyle.MODEL_SCREEN_BG);
        screenContext.transform.ResizeWindow(getWidth(), getHeight());
        g2d.setTransform(screenContext.transform.getTransform());
        OriModel model = this.screenContext.palette.getOriModel();
        Folder.fold(model, new FoldPolicy());
        OriDrawer.drawModel(g2d, model);
        OriDrawer.drawStepNo(g2d, screenContext.palette.getStepNo() + 1, 100);

    }
}
