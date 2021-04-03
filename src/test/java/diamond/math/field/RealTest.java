/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.math.field;

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
        assert (a.add(b).toString().equals(new Real(42.42).toString()));
        assert (a.mul(b).toString().equals(new Real(17.64).toString()));
        assert (a.div(c).toString().equals(new Real(10).toString()));
    }

}
