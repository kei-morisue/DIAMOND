/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.metric;

import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class M<T extends F<T>> {

    public abstract M<T> add(M<T> m);

    public abstract M<T> neg();

    public abstract F<T> prod(M<T> m);

    public abstract M<T> scale(F<T> f);

    public M<T> sub(M<T> m) {
        return add(m.neg());
    };

    public F<T> norm() {
        return prod(this);
    };

}
