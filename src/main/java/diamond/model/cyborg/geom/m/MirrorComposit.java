/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.m;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentBase;

/**
 * @author Kei Morisue
 *
 */
public class MirrorComposit extends AbstractMirror {
    private final static Vertex O = new Vertex(.0, .0);

    @Deprecated
    public MirrorComposit() {
        super();
    }

    public MirrorComposit(SegmentBase segment, AbstractMirror mirror0) {
        compose(new MirrorPlain(segment), mirror0);
    }

    // A1( A0.x + B0 ) + B1 = A1.A0.x + (A1.B0 + B1).
    // A1.A0 = M^(1-(f0+f1)/2).R(t0+f0.t1)
    public void compose(AbstractMirror m0, AbstractMirror m1) {
        boolean f0 = m0.isFlip;
        boolean f1 = m1.isFlip;
        this.isFlip = f0 != f1;
        setT(m0.t + m0.pm() * m1.t);
        this.b = applyA(m1, m0.b).add(m1.b);
    }

    private Direction applyA(AbstractMirror m1, Direction b0) {
        return m1.apply(b0.ver(O)).dir(O);
    }

}
