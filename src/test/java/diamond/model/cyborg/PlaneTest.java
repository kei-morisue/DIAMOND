/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

/**
 * @author Kei Morisue
 *
 */
public class PlaneTest extends AbstractPaintActionTest {

    public PlaneTest() {
        super();
    }

    @Test
    public void Step0() {
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 4, 0);
    }

}
