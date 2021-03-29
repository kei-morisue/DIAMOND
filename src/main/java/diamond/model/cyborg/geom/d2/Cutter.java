/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Cutter {
    @Deprecated
    private Cutter() {
    }

    public static <T extends F<T>> void cut(
            Step<T> step,
            Face<T> face,
            Seg<T> s) {

        Face<T> f0 = new Face<T>();
        Face<T> f1 = new Face<T>();
        new Link<T>(f0, s);
    }
}
