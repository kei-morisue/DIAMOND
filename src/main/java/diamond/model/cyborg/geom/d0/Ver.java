/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.awt.Graphics2D;

import diamond.model.cyborg.Util;
import diamond.model.cyborg.graphics.draw.VerDrawer;
import diamond.model.cyborg.graphics.find.Metric;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class Ver<T extends F<T>> implements Metric<T> {
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

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            boolean isPointed) {
        VerDrawer.draw(screen, g2d, this, isPointed);
    }

    public boolean equals(Ver<T> v0) {
        return dir(v0).x.isZero() && dir(v0).y.isZero();
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

    @Override
    public String toString() {
        return x.toString() + ", " + y.toString();
    }

}
