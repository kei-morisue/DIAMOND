/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d1.SegmentCrease;

/**
 * @author Kei Morisue
 *
 */
public class SS extends AxiomGenerator<AbstractSegment, AbstractSegment> {

    @Override
    SegmentCrease allign(AbstractSegment t1, AbstractSegment t2) {
        if (t1 == t2) {
            return null;
        }
        return null;
    }

}
