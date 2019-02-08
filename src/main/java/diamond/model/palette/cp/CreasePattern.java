/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp;

import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class CreasePattern {
    private Set<OriLine> lines = new HashSet<>();
    private Set<OriLine> cutLines = new HashSet<>();

    public CreasePattern() {
        buildWhitePaper(Initials.PAPER_SIZE, Initials.PAPER_EDGES);
        for (OriLine line : lines) {
            cutLines.add(line);
        }
    }

    public void buildWhitePaper(double size, int edges) {
        lines = WhitePaperBuilder.build(size, edges);
    }

    public Set<OriLine> getLines() {
        return this.lines;
    }

    public void addLine(OriLine line) {
        new LineAdder().addLine(line, lines);
    }

    public Set<OriLine> getCutLines() {
        return cutLines;
    }

}
