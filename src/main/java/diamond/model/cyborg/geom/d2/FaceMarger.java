/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class FaceMarger {

    public static <T extends F<T>> Face<T> marge(
            Face<T> f0,
            Face<T> f1,
            Edge<T> edge) {
        return new Face<T>(f0, f1, edge);
    }
}
