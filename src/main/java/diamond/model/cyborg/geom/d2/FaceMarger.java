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

    public static <T extends F<T>> Face<T> marge(Face<T> f0, Edge<T> edge) {
        Face<T> f = new Face();
        //        f.add(loop);
        //        f.add(f0.loop);
        //        f.remove(edge);
        //        f.remove(edge);
        //        f.order();
        //        for (Seg<T> crease : creases) {
        //            f.add(crease);
        //        }
        //        for (Seg<T> crease : f0.creases) {
        //            f.add(crease);
        //        }
        //        f.add(new Seg<T>(edge));
        return f;
    }
}
