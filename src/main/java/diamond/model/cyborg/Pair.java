/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.Collection;

/**
 * @author Kei Morisue
 *
 */
public class Pair<T> {
    public T p;
    public T q;

    public Pair(T p, T q) {
        this.p = p;
        this.q = q;
    }

    public void add(Collection<T> ts) {
        ts.add(p);
        ts.add(q);
    }
}
