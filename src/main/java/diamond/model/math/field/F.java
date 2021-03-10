/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public abstract class F<T> {
    public F() {
    }

    public abstract F<T> add(F<T> f);

    public abstract F<T> neg();

    public abstract F<T> mul(F<T> f);

    public abstract F<T> invImpl();

    public abstract boolean isZero();

    public F<T> inv() {
        if (isZero()) {
            System.out.println(Labels.get("div0"));
            return this;
        }
        return invImpl();
    };

    public F<T> sub(F<T> f) {
        return add(f.neg());
    };

    public F<T> div(F<T> f) {
        return mul(f.inv());
    }

    @Override
    public abstract String toString();
}
