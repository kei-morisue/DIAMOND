/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.math.field;

import org.junit.Test;

import diamond.model.math.field.Rational;

/**
 * @author Kei Morisue
 *
 */
public class RationalTest {
    private final static Rational a = new Rational(1, 2);
    private final static Rational b = new Rational(1, 3);
    private final static Rational c = new Rational(5, 6);

    @Test
    public void test0() {
        assert (a.add(b).toString().equals(new Rational(5, 6).toString()));
        assert (a.mul(b).toString().equals(new Rational(1, 6).toString()));
        assert (a.div(c).toString().equals(new Rational(3, 5).toString()));
    }

    @Test
    public void test1() {
        assert (new Rational(1000, 0)
                .toString().equals(new Rational(-1, -1)
                        .toString()));
    }

    @Test
    public void test2() {
        assert (new Rational(1, -2)
                .toString().equals(new Rational(-1, 2)
                        .toString()));
    }

    @Test
    public void test3() {
        assert (new Rational(2, 4)
                .toString().equals(new Rational(1, 2)
                        .toString()));
    }
}
