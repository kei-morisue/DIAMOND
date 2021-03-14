/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0.mirror;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class MirrorPlain<T extends F<T>> extends AbstractMirror<T> {
    @Deprecated
    public MirrorPlain() {
        super();
    }

    // R(s).(M.R(-s)(x - v0)) + v0
    // R(s).M.R(-s).x - R(s).M.R(-s).v0 + v0
    // A.x + B : Affine Transform
    // A := R(s).M.R(-s) = M.R(-2s) = M.R(t)
    // B := v0 - A.v0
    public MirrorPlain(Ver<T> v0, Ver<T> v1) {
        Dir<T> d = v1.dir(v0);
        F<T> c = d.x;
        F<T> s = d.y;
        cos = c.mul(c).sub(s.mul(s)).div(d.norm());
        sin = c.mul(s).mul(-2).div(d.norm());
        isFlip = true;
        b = ((Dir<T>) applyA(v0).neg()).ver(v0);
    }

}
