/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math.field;

/**
 * @author Kei Morisue
 *
 */
public class Rational extends F<Rational> {
    public static final Rational ONE = new Rational(1, 1);
    public static final Rational ZERO = new Rational(0, 1);
    private static final long denom = 1;
    private long n = 1;
    private long d = 1;

    @Deprecated
    public Rational() {
    }

    public Rational(Rational r) {
        n = r.n;
        d = r.d;
    }

    public Rational(double a) {
        n = (long) (a * denom);
        d = denom;
        reduce();
    }

    public Rational(long n, long d) {
        if (d == 0) {
            return;
        }
        if (d < 0) {
            this.n = -n;
            this.d = -d;
            reduce();
            return;
        }
        this.n = n;
        this.d = d;
        reduce();
    }

    public static long gcd(long a, long b) {
        long r;
        while (b > 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    private void reduce() {
        long r = (n < 0) ? gcd(-n, d) : gcd(n, d);
        n = n / r;
        d = d / r;
    }

    public boolean isZ() {
        return d == 1;
    }

    public boolean isNEven() {
        return n % 2 == 0;
    }

    public boolean isDEven() {
        return d % 2 == 0;
    }

    public boolean isSquared() {
        return isNSquared() && isDSquared();
    }

    public boolean isDSquared() {
        return Quad.isSquared(d);
    }

    public boolean isNSquared() {
        return Quad.isSquared(n);
    }

    @Override
    public F<Rational> add(F<Rational> f) {
        Rational g = (Rational) f;
        Rational r = new Rational(n * g.d + g.n * d, d * g.d);
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
        if (isZero()) {
            return "0";
        }
        return Long.toString(n) + "/" + Long.toString(d);
    }

    @Override
    public boolean isZero() {
        return n == 0;
    }

    @Deprecated
    public long getN() {
        return n;
    }

    @Deprecated
    public void setN(int n) {
        this.n = n;
    }

    @Deprecated
    public long getD() {
        return d;
    }

    @Deprecated
    public void setD(int d) {
        this.d = d;
    }

    @Override
    public double d() {
        return (double) n / d;
    }

    @Override
    public F<Rational> mul(int i) {
        return new Rational(n * i, d);
    }

    @Override
    public F<Rational> div(int i) {
        return new Rational(n, d * i);
    }

    @Override
    public F<Rational> sqrt() {
        if (isSquared()) {
            return new Rational(
                    Quad.sqrt(n),
                    Quad.sqrt(d));
        }
        return new Rational((int) Math.sqrt(d()), 1);

    }

    @Override
    public boolean isNeg() {
        return n < 0;
    }

}
