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
public class FoldedScreen extends AbstractScreen {

    private Context context;

    public FoldedScreen(Context context) {
        this.context = context;
        context.setFoldedScreen(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBackGround(g2d, Skin.BG_FOLDED_SCREEN);
        //TODO
    }

}
