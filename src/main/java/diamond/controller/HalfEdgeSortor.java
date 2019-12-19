/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Comparator;

import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeSortor implements Comparator<HalfEdge> {
    private HalfEdge h0;

    public HalfEdgeSortor(HalfEdge h0) {
        this.h0 = h0;
    }

    @Override
    public int compare(HalfEdge h1, HalfEdge h2) {
        HalfEdge h = h0;
        while (true) {
            if (h == h1) {
                return 1;
            } else if (h == h2) {
                return -1;
            }
            h = h.getNext();
        }
    }

}
