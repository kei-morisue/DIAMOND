/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.d2.Face;

/**
 * @author Kei Morisue
 *
 */
public class SegmentMV extends AbstractSegment {
    private boolean isM = false;
    private Face f0 = null;
    private Face f1 = null;

    public SegmentMV(Face f0) {
        this.setF0(f0);
    }

    public boolean isM() {
        return isM;
    }

    public void setMountain(boolean isM) {
        this.isM = isM;
    }

    public Face getF1() {
        return f1;
    }

    public void setF1(Face f1) {
        this.f1 = f1;
    }

    public Face getF0() {
        return f0;
    }

    public void setF0(Face f0) {
        this.f0 = f0;
    }
}
