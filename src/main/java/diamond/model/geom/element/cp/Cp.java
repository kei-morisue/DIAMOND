/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.cp;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.util.OriFaceUtil;
import diamond.model.palette.cp.editor.LineAdder;

/**
 * @author long_
 *
 */
public class Cp {
    private Set<OriLine> lines = new HashSet<>();
    private AffineTransform affineTransform = new AffineTransform();
    private Double baseFaceCenter = new Double();
    private OriModel oriModel;

    public Cp() {
        buildWhitePaper(Initials.PAPER_SIZE, Initials.PAPER_EDGES);
    }

    public Cp(Cp cp) {
        for (OriLine oriLine : cp.getLines()) {
            OriLine line = new OriLine(oriLine);
            if (line.getType() == LineType.UNSETTLED_MOUNTAIN) {
                line.setType(LineType.MOUNTAIN);
            }
            if (line.getType() == LineType.UNSETTLED_VALLEY) {
                line.setType(LineType.VALLEY);
            }
            lines.add(line);
        }
        baseFaceCenter = OriFaceUtil.getCenterPoint(cp.oriModel.getBaseFace());
        oriModel = new OriModel(this);
    }

    public void rebuildModel() {
        oriModel.build(this);
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
        LineAdder.addLine(line, lines);
    }

    public void addAll(Collection<OriLine> lines) {
        LineAdder.addAll(lines, this.lines);
    }

    public Set<OriLine> getCutLines() {
        Set<OriLine> cutLines = new HashSet<>();
        for (OriLine line : lines) {
            if (line.type == LineType.CUT) {
                cutLines.add(line);
            }
        }
        return cutLines;
    }

    public OriModel getOriModel() {
        return this.oriModel;
    }

    @Deprecated // just for XML encorder
    public void setOriModel(OriModel oriModel) {
        this.oriModel = oriModel;
    }

    @Deprecated // just for XML encorder
    public void setLines(Set<OriLine> lines) {
        this.lines = lines;
    }

    public Double getBaseFaceCenter() {
        return baseFaceCenter;
    }

    public void setBaseFaceCenter(Double baseFaceCenter) {
        this.baseFaceCenter = baseFaceCenter;
    }

}
