/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class FaceMargeable<T extends F<T>> extends FaceCuttable<T> {
    @Deprecated
    public FaceMargeable() {
        super();
    }

    public FaceMargeable(Link<T> edge) {
        super(edge);
    }

    public Face<T> marge(Face<T> f0, Link<T> edge) {
        Face<T> f = new Face();
        f.add(edges);
        f.add(f0.edges);
        f.remove(edge);
        f.remove(edge);
        f.order();
        for (Seg<T> crease : creases) {
            f.add(crease);
        }
        for (Seg<T> crease : f0.creases) {
            f.add(crease);
        }
        f.add(new Seg<T>(edge));
        return f;
    }
}
