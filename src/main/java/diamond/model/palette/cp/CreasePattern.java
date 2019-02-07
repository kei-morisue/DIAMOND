/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp;

import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class CreasePattern {
    private Set<OriLine> lines;

    public CreasePattern() {
        buildWhitePaper(Initials.PAPER_SIZE, Initials.PAPER_EDGES);
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

}
