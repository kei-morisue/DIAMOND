/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d1.SegmentBase;
import diamond.model.cyborg.geom.d1.SegmentCrease;

/**
 * @author Kei Morisue
 *
 */
public class SS extends AxiomGenerator<SegmentBase, SegmentBase> {

    @Override
    SegmentCrease allign(SegmentBase t1, SegmentBase t2) {
        if (t1 == t2) {
            return null;
        }
        return null;
    }

}
