/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.math.field;

import static org.junit.Assert.*;

import org.junit.Test;

import diamond.model.math.field.Real;

/**
 * @author Kei Morisue
 *
 */
public class RealTest {
    private final static Real a = new Real(42);
    private final static Real b = new Real(0.42);
    private final static Real c = new Real(4.2);

    @Test
    public void test0() {
        assertFalse(a.add(b).toString() == new Real(42.42).toString());
        assertFalse(a.mul(b).toString() == new Real(17.64).toString());
        assertFalse(a.div(c).toString() == new Real(10).toString());
    }

}
