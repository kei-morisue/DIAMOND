/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Line<T extends F<T>> extends Seg<T> {
    private Dir<T> u;

    @Deprecated
    public Line() {
    }

    public Line(Ver<T> p, Ver<T> q) {
        super(p, q);
        u = dir().u();
    }

    //    public Ver<T> xsection(D1<T> s) {
    //        // TODO 自動生成されたメソッド・スタブ
    //
    //    }

}
