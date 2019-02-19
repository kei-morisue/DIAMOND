/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.palette.cp.editor.LineAdder;

/**
 * @author long_
 *
 */
public class CreasePattern {
    private Set<OriLine> lines = new HashSet<>();

    @Deprecated // just for XML encorder
    public void setLines(Set<OriLine> lines) {
        this.lines = lines;
    }

    public CreasePattern() {
        buildWhitePaper(Initials.PAPER_SIZE, Initials.PAPER_EDGES);
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
