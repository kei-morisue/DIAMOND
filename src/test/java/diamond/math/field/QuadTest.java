/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.math.field;

import static org.junit.Assert.*;

import org.junit.Test;

import diamond.model.math.field.Quad;
import diamond.model.math.field.Real;

/**
 * @author Kei Morisue
 *
 */
public class QuadTest {
    private final static Real a = new Real(1);
    private final static Real b = new Real(-1);
    private final static Real c = new Real(-1);

    @Test
    public void test0() {
        Real root0 = Quad.root0(a, b, c);
        assertEquals(root0.d(), (1 + Math.sqrt(5)) / 2, 1e-10);
        Real root1 = Quad.root1(root0, a, c);
        assertEquals(root1.d(), (1 - Math.sqrt(5)) / 2, 1e-10);
    }

}
