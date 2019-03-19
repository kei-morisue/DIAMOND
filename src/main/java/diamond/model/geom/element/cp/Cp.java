/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.cp;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.palette.cp.editor.LineAdder;

/**
 * @author long_
 *
 */
public class Cp {
    private Set<OriLine> lines = new HashSet<>();
    private Point2D.Double origin = new Point2D.Double();

    @Deprecated // just for XML encorder
    public void setLines(Set<OriLine> lines) {
        this.lines = lines;
    }

    public Cp() {
        buildWhitePaper(Initials.PAPER_SIZE, Initials.PAPER_EDGES);
    }

    public Cp(Cp creasePattern) {
        for (OriLine oriLine : creasePattern.getLines()) {
            lines.add(new OriLine(oriLine));
        }
    }

    public Point2D.Double getOrigin() {
        return this.origin;
    }

    public void setOrigin(Point2D.Double origin) {
        this.origin = origin;
    }

    public void buildWhitePaper(double size, int edges) {
        lines = WhitePaperBuilder.build(size, edges);
    }

    public Set<OriLine> getLines() {
        return this.lines;
    }

    public void clear() {
        lines.clear();
    }

    public void addLine(OriLine line) {
        new LineAdder().addLine(line, lines);
    }

    public void addAll(Collection<OriLine> lines) {
        new LineAdder().addAll(lines, this.lines);
    }

    public Set<OriLine> getCutLines() {
        Set<OriLine> cutLines = new HashSet<>();
        for (OriLine line : lines) {
            cutLines.add(line);
        }
        return cutLines;
    }

}
