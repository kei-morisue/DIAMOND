/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.math.field;

import static org.junit.Assert.*;

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
        assertFalse(a.add(b).toString() == new Silver(
                new Rational(2, 1),
                new Rational(3, 1)).toString());
        assertFalse(a.mul(b).toString() == new Silver(
                new Rational(9, 1),
                new Rational(2, 1)).toString());
        assertFalse(a.div(c).toString() == new Silver(
                new Rational(1, 3),
                new Rational(1, 3)).toString());
    }

}
