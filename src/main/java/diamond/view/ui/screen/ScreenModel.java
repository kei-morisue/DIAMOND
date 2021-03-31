/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;

import diamond.controller.Context;
import diamond.controller.action.screen.ScreenModelAction;
import diamond.model.math.field.F;
import diamond.view.ui.screen.style.Skin;

/**
 * @author Kei Morisue
 *
 */
public final class ScreenModel<T extends F<T>> extends AbstractScreen<T> {
    private ScreenModelAction<T> screenAction;

    public ScreenModel(Context<T> context) {
        super(context);
        this.screenAction = new ScreenModelAction<T>(context,
                this);
        addMouseListener(screenAction);
        addMouseMotionListener(screenAction);
        addMouseWheelListener(screenAction);
    }

    @Override
    protected Color getBGColor() {
        return Skin.BG_STEP_SCREEN;
    }

    @Override
    protected void drawStep(Graphics2D g2d) {
        getStep().draw(this, g2d, (float) getScale(), false);
        screenAction.draw(g2d);
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    protected void drawPointed(Graphics2D g2d) {
    }

}
