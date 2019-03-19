/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.cp;

import java.util.HashSet;

import diamond.model.geom.element.LineType;

/**
 * @author long_
 *
 */
public class WhitePaperBuilder {

    private static final double rotationOffset = Math.PI / 2;

    public static HashSet<OriLine> build(double size, int edges) {
        HashSet<OriLine> lines = new HashSet<>();
        double theta = Math.PI / ((double) edges);
        double radius = size * 0.5 / Math.sin(theta);
        for (int i = 0; i < edges; i++) {
            OriLine line = new OriLine(
                    buildOriPoint(radius, rotationOffset + theta * (2 * i - 1)),
                    buildOriPoint(radius, rotationOffset + theta * (2 * i + 1)),
                    LineType.CUT);
            lines.add(line);
        }
        return lines;
    }

    private static OriPoint buildOriPoint(double radius, double radian) {
        return new OriPoint(radius * Math.cos(radian),
                radius * Math.sin(radian));
    }
}
