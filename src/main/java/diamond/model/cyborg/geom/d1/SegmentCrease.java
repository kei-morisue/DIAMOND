/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

/**
 * @author Kei Morisue
 *
 */
public class SegmentCrease extends AbstractSegment {
    private boolean isM = false;

    public SegmentCrease() {
    }

    public boolean isM() {
        return isM;
    }

    public void setMountain(boolean isM) {
        this.isM = isM;
    }

}
