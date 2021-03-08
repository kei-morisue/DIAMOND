/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.d2.D2;

/**
 * @author Kei Morisue
 *
 */
public interface D1<T> {
    public T dist(D1<T> d);

    public T dist(D2<T> d);

}
