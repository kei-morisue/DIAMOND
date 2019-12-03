/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import diamond.Config;
import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class PlaneTest {
    private Context context = new Context();
    private Cp cp = context.getCp();
    private LinkedList<Face> faces = cp.getFaces();
    private static final double l = Config.PAPER_SIZE;

    @Test
    public void Step0() {
        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 4, 0);
    }

}
