/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Cp<T extends F<T>> {
    protected ArrayList<Face<T>> faces = new ArrayList<>();
    protected HashSet<Link<T>> links = new HashSet<>();

    @Deprecated
    public Cp() {
    }

    public Cp(ArrayList<Face<T>> faces,
            Face<T> baseFace) {
        //TODO
    }

}
