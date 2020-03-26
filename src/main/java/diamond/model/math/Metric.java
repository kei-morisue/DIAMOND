/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math;

import diamond.model.cyborg.geom.AbstractSegment;
import diamond.model.cyborg.geom.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class Metric {
    public static double distance(AbstractSegment segment, Vertex v) {
        Vertex v0 = segment.getV0();
        Vertex v1 = segment.getV1();
        return distance(v0, v1, v);
    }

    public static double distance(Vertex v0, Vertex v1, Vertex v) {
        Vertex a = v0.sub(v);
        Vertex b = v1.sub(v);
        Vertex c = v1.sub(v0);
        return a.outer(b) / c.norm();
    }

    public static Vertex getFoot(Vertex v0, Vertex v1, Vertex v) {
        Vertex a = v.sub(v0);
        Vertex b = v1.sub(v0);
        return v0.add(b.scale(getProjection(a, b)));
    }

    public static boolean isOn(AbstractSegment segment, Vertex v) {
        Vertex v0 = segment.getV0();
        Vertex v1 = segment.getV1();
        return isOn(v0, v1, v);
    }

    public static boolean isOn(Vertex v0, Vertex v1, Vertex v) {
        double d = distance(v0, v1, v);
        Vertex a = v.sub(v0);
        Vertex b = v1.sub(v0);
        double p = getProjection(a, b);
        return (Fuzzy.isSmall(p) && Fuzzy.isSmall(d))
                ? true
                : false;
    }

    private static double getProjection(Vertex a, Vertex b) {
        return a.prod(b) / b.prod(b);
    }
}
