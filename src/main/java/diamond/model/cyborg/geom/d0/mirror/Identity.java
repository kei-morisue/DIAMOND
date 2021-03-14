/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0.mirror;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Identity<T extends F<T>> implements Mirror<T> {

    @Override
    public Ver<T> apply(Ver<T> v) {
        return new Ver<T>(v.x, v.y);
    }

}
