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
public class Rational extends F<Rational> {
    private int n = 1;
    private int d = 1;

    public Rational(int n, int d) {
        if (d < 0) {
            this.n = -n;
            this.d = -d;
        } else if (0 < d) {
            this.n = n;
            this.d = d;
        } else {
            System.out.println(Labels.get("div0"));
        }
    }

    public static int gcd(int a, int b) {
        int r = 0;
        while (b > 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private void reduce() {
        int r = gcd(n, d);
        n = n / r;
        d = d / r;
    }

    @Override
    public F<Rational> add(F<Rational> f) {
        Rational g = (Rational) f;
        Rational r = new Rational(n * g.d + g.n + d, d * g.d);
        r.reduce();
        return r;
    }

    @Override
    public Rational neg() {
        return new Rational(-n, d);
    }

    @Override
    public F<Rational> mul(F<Rational> f) {
        Rational g = (Rational) f;
        Rational r = new Rational(n * g.n, d * g.d);
        r.reduce();
        return r;
    }

    @Override
    public Rational invImpl() {
        return new Rational(d, n);
    }

    @Override
    public String toString() {
        return Integer.toString(n) + "/" + Integer.toString(d);
    }

    @Override
    public boolean isZero() {
        return n == 0;
    }

}
