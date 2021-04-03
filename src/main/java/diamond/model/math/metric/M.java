/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.metric;

/**
 * @author Kei Morisue
 *
 */
public abstract class M<T extends M<T, ?>, F> {

    public abstract T add(T m);

    public abstract T neg();

    public abstract F prod(T m);

    public abstract T scale(F f);

    public T sub(T m) {
        return add(m.neg());
    };

    public abstract F norm();

}
