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
    public static final Silver ZERO = new Silver(Rational.ZERO, Rational.ZERO);
    public static final Silver ONE = new Silver(Rational.ONE, Rational.ZERO);
    private static final Rational TWO = new Rational(2);
    private static final double SQRT2 = Math.sqrt(2);

    @Deprecated
    public Silver() {
    }

    public Silver(Rational a, Rational b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Silver add(Silver f) {
        Silver g = (Silver) f;
        return new Silver(a.add(g.a), b.add(g.b));
    }

    @Override
    public Silver neg() {
        return new Silver(a.neg(), b.neg());
    }

    @Override
    public Silver mul(Silver f) {
        Silver g = (Silver) f;
        return new Silver(
                a.mul(g.a).add(TWO.mul(b.mul(g.b))),
                a.mul(g.b).add(b.mul(g.a)));
    }

    @Override
    public Silver invImpl() {
        Rational d = a.mul(a).sub(b.mul(b).mul(TWO));
        return new Silver(a.div(d), b.neg().div(d));
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
        return a.d() + b.d() * SQRT2;
    }

    @Override
    public Silver mul(int i) {
        return new Silver(a.mul(i), b.mul(i));
    }

    @Override
    public Silver div(int i) {
        return new Silver(a.div(i), b.div(i));
    }

    @Override
    // TODO
    public Silver sqrt() {
        if (isZero()) {
            return zero();
        }
        if (b.isZero()) {
            if (a.isSquared()) {
                return new Silver(a.sqrt(), Rational.ZERO);
            }
        }
        return new Silver(
                new Rational(1),
                new Rational(0));
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

    @Override
    public Silver zero() {
        return ZERO;
    }

    @Override
    public Silver one() {
        return ONE;
    }

}
