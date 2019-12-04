/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.FoldedScreenAction;
import diamond.view.ui.screen.draw.FoldedScreenDrawer;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public class FoldedScreen extends AbstractScreen {

    private Context context;

    public FoldedScreen(Context context) {
        this.context = context;
        context.setFoldedScreen(this);
        FoldedScreenAction screenAction = new FoldedScreenAction(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
        transform.zoom(0.5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, Skin.BG_FOLDED_SCREEN);
        transform.resize(getWidth(), getHeight());
        g2d.setTransform(transform);
        FoldedScreenDrawer.draw(g2d, context.getCp());
        FoldedScreenDrawer.draw(g2d, context);
    }

}
