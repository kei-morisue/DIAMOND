/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

/**
 * @author Kei Morisue
 *
 */
public class Real extends F<Real> {
    private double a = 1;

    @Deprecated
    public Real() {
    }

    public Real(double a) {
        this.a = a;
    }

    @Override
    public F<Real> add(F<Real> f) {
        return new Real(a + ((Real) (f)).a);
    }

    @Override
    public F<Real> neg() {
        return new Real(-a);
    }

    @Override
    public F<Real> mul(F<Real> f) {
        return new Real(a * ((Real) (f)).a);
    }

    @Override
    public F<Real> invImpl() {
        return new Real(1.0 / a);
    }

    @Override
    public String toString() {
        return Double.toString(a);
    }

    @Override
    public boolean isZero() {
        return a == 0;
    }

    @Deprecated
    public double getA() {
        return a;
    }

    @Deprecated
    public void setA(double a) {
        this.a = a;
    }

    @Override
    public double d() {
        return a;
    }

}
