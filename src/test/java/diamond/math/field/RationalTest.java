/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.math.field;

import static org.junit.Assert.*;

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
        assertFalse(a.add(b).toString() == new Rational(5, 6).toString());
        assertFalse(a.mul(b).toString() == new Rational(1, 6).toString());
        assertFalse(a.div(c).toString() == new Rational(3, 5).toString());
    }

}
