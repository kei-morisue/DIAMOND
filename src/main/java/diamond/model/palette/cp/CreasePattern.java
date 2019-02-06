/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp;

import java.util.HashSet;
import java.util.Set;

import javax.vecmath.Vector2d;

import diamond.Initials;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.CrossPointUtil;

/**
 * @author long_
 *
 */
public class CreasePattern {
    private Set<OriLine> lines;
    private Set<OriPoint> points;

    public CreasePattern() {
        buildWhitePaper(Initials.PAPER_SIZE, Initials.PAPER_EDGES);
    }

    public void buildWhitePaper(double size, int edges) {
        lines = WhitePaperBuilder.build(size, edges);
        points = new HashSet<>(edges);
        for (OriLine l : lines) {
            points.add(l.p0);
        }
    }

    public Set<OriLine> getLines() {
        return this.lines;
    }

    public Set<OriPoint> getPoints() {
        return this.points;
    }

    public void addLine(OriLine line) {
        for (OriLine cpLine : lines) {
            Vector2d crossPoint = CrossPointUtil.getCrossPoint(line, cpLine);
            if (crossPoint != null) {
                this.points.add(new OriPoint(crossPoint.x, crossPoint.y));
            }
        }

        this.lines.add(line);
        this.points.add(line.p0);
        this.points.add(line.p1);
    }

    public void addPoint(OriPoint p) {
        points.add(p);
    }
}
