/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.Stack;

import diamond.model.cyborg.geom.d0.PivotComparator;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;

/**
 * @author Kei Morisue
 *
 */
public class CreaseAdder {
    private Stack<SegmentCrease> tobe = new Stack<>();
    private Stack<Vertex> at0 = new Stack<>();
    private Stack<Vertex> at1 = new Stack<>();

    public void across(SegmentCrease s1, Face face) {
        for (SegmentCrease s0 : face.getCreases()) {
            Vertex[] cp = s0.split(s1);
            // doesnt across
            if (cp == null) {
                continue;
            }
            Vertex cp0 = cp[0];
            Vertex cp1 = cp[1];
            // Connected
            if (cp0 == cp1) {
                continue;
            }
            record(s1, s0, cp0, cp1);
        }
        split0();
        face.add(s1);
        split1(s1);
    }

    private void record(SegmentCrease s1, SegmentCrease s0, Vertex cp0,
            Vertex cp1) {
        // only s0 is splitted
        // Could it be?
        if (cp1 == s1.getV0() || cp1 == s1.getV1()) {
            tobe.add(s0);
            at0.add(cp1);
            return;
        }
        // only s1 is splitted
        if (cp0 == s0.getV0() || cp0 == s0.getV1()) {
            at1.add(cp0);
            return;
        }
        // Both of them are splitted
        tobe.add(s0);
        at0.add(cp0);
        at1.add(cp0);
        return;
    }

    private void split1(SegmentCrease s1) {
        at1.sort(new PivotComparator(s1.getV0()));
        while (!at1.isEmpty()) {
            Vertex v = at1.pop();
            s1.split(v);
        }
    }

    private void split0() {
        while (!at0.isEmpty()) {
            SegmentCrease c = tobe.pop();
            Vertex v = at0.pop();
            c.split(v);
        }
    }

}
