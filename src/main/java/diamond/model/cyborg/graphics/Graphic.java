/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public interface Graphic<T extends F<T>> extends Drawable<T> {
    public boolean isNear(double x, double y, double scale);

    public double distSquare(double x, double y);

}
