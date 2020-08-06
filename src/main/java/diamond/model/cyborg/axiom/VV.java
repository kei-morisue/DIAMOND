/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;

/**
 * @author Kei Morisue
 *
 */
public class VV extends AxiomGenerator<Vertex, Vertex> {

    @Override
    SegmentCrease allign(Vertex t1, Vertex t2) {
        if (t1 == t2) {
            return null;
        }
        // Perpendicular bisector
        return null;
    }

}
