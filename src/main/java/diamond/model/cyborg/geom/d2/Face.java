/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.geom.d1.Link;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Face<T extends F<T>> extends FaceMargeable<T> {
    @Deprecated
    Face() {
        super();
    }

    public Face(Link<T> edge) {
        super(edge);
    }

}
