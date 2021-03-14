/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public interface StateClick<T extends F<T>> {
    public AbstractScreenState<T> left(Ver<T> v);

    public AbstractScreenState<T> right(Ver<T> v);

    public AbstractScreenState<T> left(D1<T> s);

    public AbstractScreenState<T> right(D1<T> s);
}
