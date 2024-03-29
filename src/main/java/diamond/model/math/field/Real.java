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
    private static final double EPS = 1e-8;
    public static Real ZERO = new Real(0);
    public static Real ONE = new Real(1);

    @Deprecated
    public Real() {
    }

    public Real(double a) {
        this.a = a;
    }

    @Override
    public Real add(Real f) {
        return new Real(a + ((Real) (f)).a);
    }

    @Override
    public Real neg() {
        return new Real(-a);
    }

    @Override
    public Real mul(Real f) {
        return new Real(a * ((Real) (f)).a);
    }

    @Override
    public Real invImpl() {
        return new Real(1.0 / a);
    }

    @Override
    public String toString() {
        return Double.toString(a);
    }

    @Override
    public boolean isZero() {
        return Math.abs(a) < EPS;
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

    @Override
    public Real mul(int i) {
        return new Real(a * i);
    }

    @Override
    public Real div(int i) {
        return new Real(a / i);
    }

    @Override
    public Real sqrt() {
        return new Real(Math.sqrt(a));
    }

    @Override
    public boolean isNeg() {
        return a < 0.0;
    }

    @Override
    public Real zero() {
        return ZERO;
    }

    @Override
    public Real one() {
        return ONE;
    }
}
