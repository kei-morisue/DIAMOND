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
    private Rational a;
    private Rational b;
    private static Rational TWO = new Rational(2, 1);

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
        F<Rational> d = TWO.mul(b).mul(b).sub(a.mul(a));
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

}
