/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

/**
 * @author Kei Morisue
 *
 */
public abstract class F<T> implements Quadric<T> {
    protected F() {
    }

    public abstract F<T> add(F<T> f);

    public abstract F<T> neg();

    public abstract F<T> mul(F<T> f);

    public abstract F<T> mul(int i);

    public abstract F<T> div(int i);

    public abstract F<T> invImpl();

    public F<T> abs() {
        if (isNeg()) {
            return neg();
        }
        return this;
    };

    public abstract boolean isNeg();

    public abstract boolean isZero();

    public F<T> inv() {
        if (isZero()) {
            new Exception("div0!");
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

    public abstract double d();

}
