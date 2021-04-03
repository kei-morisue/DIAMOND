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
public class SqrtTest {
    private final static Rational a = new Rational(25, 4);
    private final static Rational b = new Rational(9, 8);
    private final static Rational c = new Rational(0);

    private final static Silver s0 = new Silver(b, c);
    private final static Silver s1 = new Silver(
            b.mul(b).add(c.mul(c).mul(2)),
            b.mul(2).mul(c));

    @Test
    public void test0() {
        assert (a.sqrt().sub(new Rational(5, 2)).isZero());
        assert (s1.sqrt().sub(s0).isZero());
    }

}
