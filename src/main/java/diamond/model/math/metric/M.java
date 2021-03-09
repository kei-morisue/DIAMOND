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
public abstract class M<T, S extends F<S>> {

    public abstract M<T, S> add(M<T, S> m);

    public abstract M<T, S> neg();

    public abstract F<S> prod(M<T, S> m);

    public abstract M<T, S> scale(F<S> f);

    public M<T, S> sub(M<T, S> m) {
        return add(m.neg());
    };

    public F<S> norm(M<T, S> m) {
        return prod(this);
    };

}
