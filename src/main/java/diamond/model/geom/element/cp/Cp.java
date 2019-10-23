/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.cp;

import java.awt.geom.Point2D.Double;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.diagram.arrow.FlipArrow;
import diamond.model.geom.element.diagram.arrow.FoldUnfoldArrow;
import diamond.model.geom.element.fold.OriFaceOrder;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.util.OriFaceUtil;
import diamond.model.palette.cp.editor.LineAdder;

/**
 * @author long_
 *
 */
public class Cp {
    private Set<OriLine> lines = new HashSet<>();
    private Double baseFaceCenter = new Double();
    private OriFaceOrder order = new OriFaceOrder();
    private OriModel oriModel;

    public Cp() {
        buildWhitePaper(Initials.PAPER_SIZE, Initials.PAPER_EDGES);
    }

    public Cp(Cp cp) {
        boolean isFlip = false;
        for (OriLine oriLine : cp.getLines()) {
            OriLine line = new OriLine(oriLine);
            if (line.getType() == LineType.UNSETTLED_MOUNTAIN) {
                line.setType(LineType.MOUNTAIN);
            }
            if (line.getType() == LineType.UNSETTLED_VALLEY) {
                line.setType(LineType.VALLEY);
            }
            if (oriLine.getArrow() != null) {
                if (oriLine.getArrow().getClass() == FoldUnfoldArrow.class) {
                    line.setType(LineType.CREASE);
                } else if (oriLine.getArrow().getClass() == FlipArrow.class) {
                    isFlip = true;
                }
            }
            lines.add(line);
        }
        OriModel originalModel = cp.oriModel;
        baseFaceCenter = OriFaceUtil
                .getCenterPoint(originalModel.getBaseFace());
        oriModel = new OriModel(this);
        cp.saveOrder();
        order = new OriFaceOrder(cp.order);
        if (isFlip) {
            order.flip();
            baseFaceCenter = order.getCenterPoints().get(0);
        }
        oriModel.setBaseFace(oriModel.getFaces().get(0));
        rebuildModel();
    }

    public void rebuildModel() {
        oriModel.build(this);
        loadOrder();
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

    public void saveOrder() {
        order.save(this);
    }

    public void loadOrder() {
        order.load(this);
    }

    public OriModel getOriModel() {
        return this.oriModel;
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

    @Deprecated
    public OriFaceOrder getOrder() {
        return this.order;
    }

    @Deprecated
    public void setOrder(OriFaceOrder order) {
        this.order = order;
    }

}
