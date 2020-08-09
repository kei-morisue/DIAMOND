/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class SegmentCrease extends AbstractSegment {

    @Deprecated
    public SegmentCrease() {
    }

    public SegmentCrease(Vertex v0, Vertex v1, SegmentType type) {
        super(v0, v1);
        this.setType(type);
    }

}
