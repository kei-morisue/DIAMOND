/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.m;

import diamond.model.cyborg.geom.d1.SegmentBase;

/**
 * @author Kei Morisue
 *
 */
public class MirrorComposit extends AbstractMirror {

    @Deprecated
    public MirrorComposit() {
        super();
    }

    public MirrorComposit(SegmentBase segment, AbstractMirror mirror) {
        compose(new MirrorPlain(segment), mirror);
    }

    // A1( A0.x + B0 ) + B1 = A1.A0.x + (A1.B0 + B1)
    // A.x + B
    // A1.A0 = M^(1 - (f0 + f1)/2).R(t0 + f0.t1)
    public void compose(AbstractMirror m0, AbstractMirror m1) {
        this.isFlip = m0.isFlip != m1.isFlip;
        setT(m0.t + m0.pm() * m1.t);
        this.b = m1.b.add(m1.applyA(m0.b.ver()));
    }
}
