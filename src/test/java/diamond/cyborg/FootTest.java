/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.cyborg;

import static org.junit.Assert.*;

import org.junit.Test;

import diamond.model.cyborg.Util;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.Rational;

/**
 * @author Kei Morisue
 *
 */
public class FootTest {
    private static final Rational ONE = new Rational(1, 1);
    private static final Rational ZERO = new Rational(0, 1);

    private Ver<Rational> v0 = new Ver<>(ONE, ZERO);
    private Ver<Rational> v1 = new Ver<>(ZERO, ONE);
    private Ver<Rational> v = new Ver<>(ONE, ONE);

    @Test
    public void VerTest0() {
        double footSquare = Util.footSquare(v0, v1, v.x.d(), v.y.d());
        assertEquals(footSquare, 0.5, 1e-10);
    }

    @Test
    public void VerTest1() {
        Ver<Rational> foot = Util.foot(v0, v1, v);
        assertEquals(foot.x.d(), 0.5, 1e-10);
        assertEquals(foot.y.d(), 0.5, 1e-10);
    }
}
