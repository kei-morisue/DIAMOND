/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp;

import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.geom.OriLine;
import diamond.model.geom.OriPoint;

/**
 * @author long_
 *
 */
public class CreasePattern {
    private Set<OriLine> lines;
    private Set<OriPoint> points;

    public CreasePattern() {
        lines = WhitePaperBuilder.build(Initials.PAPER_SIZE,
                Initials.PAPER_EDGES);
        points = new HashSet<>(Initials.PAPER_EDGES);
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
        this.lines.add(line);
        this.points.add(line.p0);
        this.points.add(line.p1);
    }

    public void addPoint(OriPoint p) {
        points.add(p);
    }
}
