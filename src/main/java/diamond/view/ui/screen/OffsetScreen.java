/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.OffsetScreenAction;
import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.draw.OffsetScreenDrawer;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public class OffsetScreen extends AbstractScreen {
    private Context context;

    public OffsetScreen(Context context) {
        this.context = context;
        OffsetScreenAction screenAction = new OffsetScreenAction(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, Skin.BG_PAINT_SCREEN);
        transform.resize(getWidth(), getHeight());
        g2d.setTransform(transform);
        OffsetScreenDrawer.draw(g2d, context);
        context.getFoldedScreen().repaint();
    }

    public void reset() {
        Cp cp = context.getCp();
        ScreenTransform transform = cp.getTransform();
        int height = getHeight();
        int width = getWidth();
        ScreenTransform screenTransform = new ScreenTransform(width, height);
        screenTransform.zoom(transform.getScale());
        screenTransform.rotate(transform.getTheta());
    }
}
