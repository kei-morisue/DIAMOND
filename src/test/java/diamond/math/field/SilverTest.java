/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.math.field;

import org.junit.Test;

import diamond.model.math.field.Rational;
import diamond.model.math.field.Silver;

/**
 * @author Kei Morisue
 *
 */
public class SilverTest {
    private final static Silver a = new Silver(
            new Rational(1, 1),
            new Rational(1, 1));
    private final static Silver b = new Silver(
            new Rational(1, 1),
            new Rational(2, 1));
    private final static Silver c = new Silver(
            new Rational(2, 1),
            new Rational(1, 1));

    @Test
    public void test0() {
        assert (a.add(b).toString().equals(new Silver(
                new Rational(2),
                new Rational(3)).toString()));
        assert (a.mul(b).toString().equals(new Silver(
                new Rational(5),
                new Rational(3)).toString()));
        assert (a.div(c).toString().equals(new Silver(
                new Rational(0),
                new Rational(1, 2)).toString()));
    }

    public void test1() {
        assert (!new Silver(
                new Rational(2, 1),
                new Rational(3, 1)).isNeg());
        assert (new Silver(
                new Rational(-9, 1),
                new Rational(2, 1)).isNeg());
        assert (new Silver(
                new Rational(-1, 1),
                new Rational(-1, 1)).isNeg());
    }
}
