/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class StepBuilder {
    public <T extends F<T>> Step<T> step0(F<T> size) {
        Ver<T> a = new Ver<T>(size, size);
        Ver<T> b = new Ver<T>(size, size.neg());
        Ver<T> c = new Ver<T>(size.neg(), size.neg());
        Ver<T> d = new Ver<T>(size.neg(), size);
        //        F<T> zero = size.neg().add(size);
        //        Ver<T> m = new Ver<T>(zero, size);
        //        Ver<T> n = new Ver<T>(zero, size.neg());

        ArrayList<Face<T>> faces = new ArrayList<>();

        Face<T> face = new Face<T>(a, b, c, d);
        face.add(new Seg<T>(a, c));
        //        face.add(new Seg<T>(m, n));

        faces.add(face);
        HashSet<Link<T>> links = new HashSet<>();
        links.add(new Link<T>(face, null, a, b));
        links.add(new Link<T>(face, null, b, c));
        links.add(new Link<T>(face, null, c, d));
        links.add(new Link<T>(face, null, d, a));

        return new Step<T>(faces, links);
    }

}
