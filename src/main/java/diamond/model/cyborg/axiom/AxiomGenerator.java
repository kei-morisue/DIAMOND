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
public abstract class AxiomGenerator<T1, T2> {
    protected SegmentBase segment = null;

    abstract SegmentCrease allign(T1 t1, T2 t2);

}
