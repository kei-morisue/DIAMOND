/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.cp;

import java.awt.geom.AffineTransform;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.palette.cp.editor.LineAdder;

/**
 * @author long_
 *
 */
public class Cp {
    private Set<OriLine> lines = new HashSet<>();
    private AffineTransform affineTransform = new AffineTransform();
    private OriModel oriModel;

    public OriModel getOriModel() {
        return this.oriModel;
    }

    public void rebuildModel() {
        oriModel.build(this);
    }

    @Deprecated // just for XML encorder
    public void setOriModel(OriModel oriModel) {
        this.oriModel = oriModel;
    }

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
        oriModel = new OriModel(this);
    }

    public AffineTransform getAffineTransform() {
        return this.affineTransform;
    }

    public void setAffineTransform(AffineTransform affineTransform) {
        this.affineTransform = affineTransform;
    }

    public void buildWhitePaper(double size, int edges) {
        lines = WhitePaperBuilder.build(size, edges);
        oriModel = new OriModel(this);

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
