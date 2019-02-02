/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp;

import java.util.HashSet;

import diamond.model.geom.OriLine;
import diamond.model.geom.OriLineType;
import diamond.model.geom.OriPoint;

/**
 * @author long_
 *
 */
public class WhitePaperBuilder {

    public static HashSet<OriLine> build(double size, int edges) {
        HashSet<OriLine> lines = new HashSet<>();
        double theta = Math.PI / ((double) edges);
        double radius = size * 0.5 / Math.sin(theta);
        for (int i = 0; i < edges; i++) {
            OriLine line = new OriLine(
                    buildOriPoint(radius, theta * (2 * i - 1)),
                    buildOriPoint(radius, theta * (2 * i + 1)),
                    OriLineType.CUT);
            lines.add(line);
        }
        return lines;
    }

    private static OriPoint buildOriPoint(double radius, double radian) {
        return new OriPoint(radius * Math.cos(radian),
                radius * Math.sin(radian));
    }
}
