/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

/**
 * @author Kei Morisue
 *
 */
public abstract class F<T extends F<T>> implements Quadric<T> {
    protected F() {
    }

    public abstract T zero();

    public abstract T one();

    public abstract T add(T f);

    public abstract T neg();

    public abstract T mul(T f);

    public abstract T mul(int i);

    public abstract T div(int i);

    public abstract T invImpl();

    public T abs() {
        if (isNeg()) {
            return neg();
        }
        return add(zero());
    };

    public abstract boolean isNeg();

    public abstract boolean isZero();

    public T inv() {
        if (isZero()) {
            new Exception("div0!");
            return zero();
        }
        return invImpl();
    };

    public T sub(T f) {
        return add(f.neg());
    };

    public T div(T f) {
        return mul(f.inv());
    }

    @Override
    public abstract String toString();

    public abstract double d();

}
