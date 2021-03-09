/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.io.Serializable;

import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Ver<T extends F<T>> implements Serializable {
    public F<T> x;
    public F<T> y;

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

}
