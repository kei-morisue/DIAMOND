/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.view.resource.color.Skin;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreen extends AbstractScreen {
    private Context context;

    public PaintScreen(Context context) {
        super();
        this.context = context;
        context.setPaintScreen(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, Skin.BG_PAINT_SCREEN);
        transform.resize(getWidth(), getHeight());
        g2d.setTransform(transform);
        PaintScreenDrawer.draw(g2d, context.getCp());
    }

}
