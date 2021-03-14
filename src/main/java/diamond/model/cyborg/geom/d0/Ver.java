/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.awt.Graphics2D;
import java.io.Serializable;

import diamond.model.cyborg.Util;
import diamond.model.cyborg.geom.Metric;
import diamond.model.cyborg.graphics.VerDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class Ver<T extends F<T>> implements Serializable, Metric {
    public F<T> x;
    public F<T> y;
    private static final double EPS = 250;

    @Deprecated
    public Ver() {
    }

    public Ver(F<T> x, F<T> y) {
        this.x = x;
        this.y = y;
    }

    public Dir<T> dir(Ver<T> v0) {
        return new Dir<T>(
                x.sub(v0.x),
                y.sub(v0.y));
    }

    public void drawPointed(ScreenModel<T> screen, Graphics2D g2d) {
        VerDrawer.drawPointed(screen, g2d, this);
    }

    @Deprecated
    public F<T> getX() {
        return x;
    }

    @Deprecated
    public void setX(F<T> x) {
        this.x = x;
    }

    @Deprecated
    public F<T> getY() {
        return y;
    }

    @Deprecated
    public void setY(F<T> y) {
        this.y = y;
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        double dx = this.x.d() - x;
        double dy = this.y.d() - y;
        return Util.norm(dx, dy) < EPS / scale / scale;
    }

    @Override
    public double distSquare(double x, double y) {
        double dx = this.x.d() - x;
        double dy = this.y.d() - y;
        return Util.norm(dx, dy);
    }

}
