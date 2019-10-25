/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow;

import java.util.Vector;

import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.diagram.arrow.body.RepeatArrowBody;
import diamond.model.geom.element.diagram.arrow.head.RepeatArrowTail;
import diamond.model.geom.element.diagram.arrow.head.ValleyFoldArrowHead;

/**
 * @author long_
 *
 */
public class RepeatArrow extends AbstractArrow {
    private Diagram d0;
    private Diagram d1;
    private Vector<Diagram> diagrams;

    public RepeatArrow(Diagram d0, Diagram d1, Vector<Diagram> diagrams) {
        this.d0 = d0;
        this.d1 = d1;
        this.diagrams = diagrams;
        buildBody();
        buildHead0();
        buildHead1();
        setScale(0.5);
    }

    @Override
    protected void buildHead0() {
        head0 = new ValleyFoldArrowHead(body, true);
    }

    @Override
    protected void buildHead1() {
        head1 = new RepeatArrowTail(body, d0, d1, diagrams);
    }

    @Override
    protected void buildBody() {
        body = new RepeatArrowBody();
    }

}
