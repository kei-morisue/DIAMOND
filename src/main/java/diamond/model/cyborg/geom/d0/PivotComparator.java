/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.util.Comparator;

/**
 * @author Kei Morisue
 *
 */
public class PivotComparator implements Comparator<Vertex> {
    private Vertex v0;

    @Deprecated
    public PivotComparator() {
    }

    public PivotComparator(Vertex v0) {
        this.v0 = v0;
    }

    @Override
    public int compare(Vertex v1, Vertex v2) {
        if (v1.dist(v0) < v2.dist(v0)) {
            return 1;
        }
        return -1;
    }

}
