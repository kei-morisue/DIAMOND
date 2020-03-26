/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom;

/**
 * @author Kei Morisue
 *
 */
public class SegmentMV extends AbstractSegment {
    private boolean isMountain = false;

    public SegmentMV() {
    }

    public boolean isMountain() {
        return isMountain;
    }

    public void setMountain(boolean isMountain) {
        this.isMountain = isMountain;
    }
}
