/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentBase;
import diamond.model.cyborg.geom.d1.SegmentCrease;

/**
 * @author Kei Morisue
 *
 */
public class VS extends AxiomGenerator<Vertex, SegmentBase> {

    @Override
    SegmentCrease allign(Vertex t1, SegmentBase t2) {
        return null;
    }

}
