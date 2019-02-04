/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp;

import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.OriLine;
import diamond.model.geom.element.OriPoint;

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

    public CreasePattern(double size) {
        buildWhitePaper(size, Initials.PAPER_EDGES);
    }

    public CreasePattern(int edges) {
        buildWhitePaper(Initials.PAPER_SIZE, edges);
    }

    public CreasePattern(double size, int edges) {
        buildWhitePaper(size, edges);
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
        this.lines.add(line);
        this.points.add(line.p0);
        this.points.add(line.p1);//TODO Cross points need also to be added
    }

    public void addPoint(OriPoint p) {
        points.add(p);
    }
}
