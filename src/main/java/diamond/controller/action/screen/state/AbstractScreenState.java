/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import diamond.controller.Context;
import diamond.controller.action.screen.Util;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreenState<T extends F<T>>
        implements StateClick<T>, KeyListener {
    protected AbstractScreenState<T> prevState;
    protected Context<T> context;
    protected Ver<T> pointedV;
    protected Seg<T> pointedS;
    protected boolean isCtrl;

    protected AbstractScreenState(
            Context<T> context,
            AbstractScreenState<T> prevState) {
        this.prevState = prevState;
        this.context = context;
    }

    public void initialize() {
        pointedV = null;
        pointedV = null;
    }

    public AbstractScreenState<T> next(
            boolean isLeft) {
        if (isLeft) {
            return onLeft();
        } else {
            return onRight();
        }
    };

    protected AbstractScreenState<T> onRight() {
        return prevState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //        isCtrl = !Util.isCtrl(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isCtrl = Util.isCtrl(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isCtrl = false;
    }

    @Override
    final public AbstractScreenState<T> right(Ver<T> v) {
        return null;
    }

    @Override
    final public AbstractScreenState<T> right(Seg<T> s) {
        return null;
    }

    protected AbstractScreenState<T> onLeft() {
        if (pointedV != null) {
            return left(pointedV);
        }
        if (pointedS != null) {
            return left(pointedS);
        }
        return this;

    }

    public abstract void drawModel(
            ScreenModel<T> screen,
            Graphics2D g2d);

    public void setPointedV(Ver<T> pointedV) {
        this.pointedV = pointedV;
    }

    public void setPointedS(Seg<T> pointedS) {
        this.pointedS = pointedS;
    }

    public Context<T> getContext() {
        return context;
    }

}
