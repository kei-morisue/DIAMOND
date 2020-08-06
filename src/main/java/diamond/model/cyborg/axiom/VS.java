/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d1.SegmentCrease;

/**
 * @author Kei Morisue
 *
 */
public class VS extends AxiomGenerator<Vertex, AbstractSegment> {

    @Override
    SegmentCrease allign(Vertex t1, AbstractSegment t2) {
        return null;
    }

}
