/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.util.AbstractCollection;
import java.util.Stack;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.PivotComparator;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.Fuzzy;
import diamond.model.math.Util;

/**
 * @author Kei Morisue
 *
 */
public class SegmentSplitter {

    public static Vertex[] split(AbstractSegment s0, AbstractSegment s1) {
        Direction d0 = s0.dir();
        Direction d1 = s1.dir();
        double det = d0.outer(d1);
        if (Fuzzy.isSmall(det)) {
            return null;
        }
        Direction d = s0.getV0().dir(s1.getV0()).scale(-1.0 / det);
        double p0 = d1.n().prod(d);
        double p1 = d0.n().prod(d);
        if (Util.in(p0, .0, 1.0) && Util.in(p1, .0, 1.0)) {
            Vertex[] vs = { s0.split(p0), s1.split(p1) };
            return vs;
        }
        return null;
    }

    //TODO Refactor
    public static void across(
            AbstractCollection<SegmentCrease> segments,
            SegmentCrease s1,
            Face face) {
        Stack<SegmentCrease> tobe = new Stack<>();
        Stack<Vertex> at0 = new Stack<>();
        Stack<Vertex> at1 = new Stack<>();
        Vertex v0 = s1.getV0();
        for (SegmentCrease s0 : segments) {
            Vertex[] cp = split(s0, s1);
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
            // only existing segment is splitted
            // Could it be?
            if (cp1 == v0 || cp1 == v0) {
                tobe.add(s0);
                at0.add(cp1);
                continue;
            }
            // only another segment is splitted
            if (cp0 == s0.getV0() || cp0 == s0.getV0()) {
                at1.add(cp0);
                return;
            }
            // Both of them are splitted
            tobe.add(s0);
            at0.add(cp0);
            at1.add(cp0);
        }
        while (!at0.isEmpty()) {
            SegmentCrease c = tobe.pop();
            Vertex v = at0.pop();
            c.split(v);
        }
        at1.sort(new PivotComparator(v0));
        SegmentType type = s1.getType();
        while (!at1.isEmpty()) {
            Vertex v = at1.pop();
            SegmentCrease c = new SegmentCrease(v0, v, type);
            face.add(c);
            v0 = v;
        }
        Vertex v1 = s1.getV1();
        SegmentCrease c = new SegmentCrease(v0, v1, type);
        face.add(c);
    }

}
