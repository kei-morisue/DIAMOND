/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0.mirror;

import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class MirrorComposit<T extends F<T>> extends AbstractMirror<T> {

    @Deprecated
    public MirrorComposit() {
        super();
    }

    // A1( A0.x + B0 ) + B1 = A1.A0.x + (A1.B0 + B1)
    // A.x + B
    // A1.A0 = M^(1 - (f0 + f1)/2).R(t0 + f0.t1)
    public MirrorComposit(AbstractMirror<T> m0, AbstractMirror<T> m1) {
        this.isFlip = m0.isFlip != m1.isFlip;
        T c0 = m0.cos;
        T c1 = m1.cos;
        T s0 = m0.sin;
        T s1 = m1.sin;
        cos = c0.mul(c1).sub(s0.mul(s1));
        if (m0.isFlip) {
            sin = s0.mul(c1).sub(c0.mul(s1));
        } else {
            sin = s0.mul(c1).add(c0.mul(s1));
        }
        this.b = m1.applyA(m0.b).ver(m1.b);
    }
}
