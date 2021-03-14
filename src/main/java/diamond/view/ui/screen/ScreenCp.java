/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;

import diamond.controller.Context;
import diamond.controller.action.screen.ScreenCpAction;
import diamond.model.math.field.F;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenCp<T extends F<T>> extends AbstractScreen<T> {

    public ScreenCp(Context<T> context) {
        super(context);
        ScreenCpAction<T> screenAction = new ScreenCpAction<T>(context, this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_MAIN_SCREEN;
    }

    @Override
    protected void drawStep(Graphics2D g2d) {
        //        Step step = diagram().getStep();
        //        step.draw(g2d, this);
        //        context.getPaintAction().onDraw(g2d);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void drawPointed(Graphics2D g2d) {
        //        pointed(g2d, Vertex.class);
        //        pointed(g2d, SegmentBase.class);
        //        pointed(g2d, Face.class);
    }

    //    private <T extends Cyborg> void pointed(Graphics2D g2d, Class<T> type) {
    //        //        PointerCyborg<T> pointer = context
    //        //                .getPointer(type);
    //        //        pointer.setG2d(g2d, this);
    //        //        pointer.draw(g2d, this);
    //        //        PickerCyborg<T> picker = context
    //        //                .getPicker(type);
    //        //        picker.setG2d(g2d, this);
    //        //        picker.draw(g2d, this);
    //    }
}
