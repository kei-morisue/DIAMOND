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
public interface StateCtrl<T extends F<T>>
        extends StateClick<T> {
    public AbstractScreenState<T> leftCtrl(Ver<T> v);

    public AbstractScreenState<T> leftCtrl(D1<T> s);

}
