/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import diamond.controller.Context;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public class ScreenPage extends AbstractScreen implements Observer {
    //    private Context context;

    protected ScreenPage() {
    }

    public ScreenPage(Context context) {
        super();
        //        this.context = context;
        //        ScreenActionPaint screenAction = new ScreenActionPaint(context, this);
        //        addMouseListener(screenAction);
        //        addMouseMotionListener(screenAction);
        //        addMouseWheelListener(screenAction);
        //        context.addObserver(this);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_MAIN_SCREEN;//TODO
    }

    @Override
    protected void draw(Graphics2D g2d) {
        //        Step step = context.getDiagram().getStep();
        //        step.draw(g2d, context.getDiagram());
        //TODO
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    public int maxPageNo() {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

    public void nextPage(int i) {
        // TODO 自動生成されたメソッド・スタブ
    }
}
