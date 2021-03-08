/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d2.D2;

/**
 * @author Kei Morisue
 *
 */
public interface D0<T> {
    public T dist(D0<T> d0);

    public T dist(D1<T> d1);

    public T dist(D2<T> d2);

}
