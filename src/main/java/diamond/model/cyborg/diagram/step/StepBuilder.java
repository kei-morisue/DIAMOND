/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.util.ArrayList;

import diamond.model.cyborg.axiom.Axioms;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Edge;
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
        Face<T> face = new Face<T>(new Edge<T>(a, b));
        face.add(new Edge<T>(b, c));
        face.add(new Edge<T>(c, d));
        face.add(new Edge<T>(d, a));
        Seg<T> crease0 = new Seg<T>(a, c);
        face.add(crease0);

        ArrayList<Face<T>> faces = new ArrayList<>();
        faces.add(face);

        Step<T> step = new Step<T>(faces);
        Seg<T> crease1 = face.add(Axioms.axiom2(a, b));
        face.add(Axioms.axiom5(a, d, crease1));

        return step;
    }

}
