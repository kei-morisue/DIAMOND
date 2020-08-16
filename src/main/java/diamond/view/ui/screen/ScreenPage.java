/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;

import diamond.controller.Context;
import diamond.controller.action.ScreenActionPaint;
import diamond.model.cyborg.style.StylePage;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenPage extends AbstractScreen {

    public ScreenPage(Context context) {
        super(context);
        ScreenActionPaint screenAction = new ScreenActionPaint(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_STEP_SCREEN;
    }

    @Override
    protected void draw(Graphics2D g2d) {
        //TODO
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void drawPointed(Graphics2D g2d) {
    }

    public int maxPageNo() {
        StylePage stylePage = diagram().getStylePage();
        byte row = stylePage.getRow();
        byte col = stylePage.getCol();
        int steps = diagram().getSteps().size() + 1;
        return 1 + steps % (row * col);
    }

    public void nextPage(int i) {
        // TODO 自動生成されたメソッド・スタブ
        repaint();
    }

}
