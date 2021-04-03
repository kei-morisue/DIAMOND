/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

/**
 * @author Kei Morisue
 *
 */
public class Silver extends F<Silver> {
    private Rational a = new Rational(1, 1);
    private Rational b = new Rational(0, 1);
    private static final Rational TWO = (Rational) Rational.ONE.mul(2);
    public static final Silver ONE = new Silver(
            Rational.ONE,
            Rational.ZERO);
    public static final Silver ZERO = new Silver(
            Rational.ZERO,
            Rational.ZERO);

    @Deprecated
    public Silver() {
    }

    public Silver(Rational a, Rational b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public F<Silver> add(F<Silver> f) {
        Silver g = (Silver) f;
        return new Silver(
                (Rational) (a.add(g.a)),
                (Rational) (b.add(g.b)));
    }

    @Override
    public F<Silver> neg() {
        return new Silver(a.neg(), b.neg());
    }

    @Override
    public F<Silver> mul(F<Silver> f) {
        Silver g = (Silver) f;
        return new Silver(
                (Rational) (a.mul(g.a).add(TWO.mul(b.mul(g.b)))),
                (Rational) (a.mul(g.b).add(b.mul(g.a))));
    }

    @Override
    public F<Silver> invImpl() {
        F<Rational> d = a.mul(a).sub(b.mul(b).mul(TWO));
        return new Silver(
                (Rational) a.div(d),
                (Rational) b.neg().div(d));
    }

    @Override
    public String toString() {
        return a.toString() + " + √2 " + b.toString();
    }

    @Override
    public boolean isZero() {
        return (a.isZero() && b.isZero());
    }

    @Deprecated
    public Rational getA() {
        return a;
    }

    @Deprecated
    public void setA(Rational a) {
        this.a = a;
    }

    @Deprecated
    public Rational getB() {
        return b;
    }

    @Deprecated
    public void setB(Rational b) {
        this.b = b;
    }

    @Override
    public double d() {
        return a.d() + b.d() * Math.sqrt(2);
    }

    @Override
    public F<Silver> mul(int i) {
        return new Silver((Rational) a.mul(i), (Rational) b.mul(i));
    }

    @Override
    public F<Silver> div(int i) {
        return new Silver((Rational) a.div(i), (Rational) b.div(i));
    }

    @Override
    // TODO
    public F<Silver> sqrt() {
        if (isZero()) {
            return ZERO;
        }
        if (b.isZero()) {
            if (a.isSquared()) {
                return new Silver((Rational) a.sqrt(), Rational.ZERO);
            }
        }
        return new Silver(
                new Rational(Math.sqrt(d())),
                Rational.ZERO);
    }

    @Override
    public boolean isNeg() {
        boolean neg = b.mul(b).mul(2).sub(a).isNeg();
        if (b.isNeg()) {
            if (a.isNeg()) {
                return true;
            }
            return neg;
        } else {
            if (!a.isNeg()) {
                return false;
            }
            return !neg;
        }
    }

}
