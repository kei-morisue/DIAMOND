/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow;

import diamond.model.geom.element.diagram.arrow.body.RepeatArrowBody;
import diamond.model.geom.element.diagram.arrow.head.RepeatArrowTail;
import diamond.model.geom.element.diagram.arrow.head.ValleyFoldArrowHead;

/**
 * @author long_
 *
 */
public class RepeatArrow extends AbstractArrow {
    public RepeatArrow() {
        super();
        setScale(0.5);
    }

    @Override
    protected void buildHead0() {
        head0 = new ValleyFoldArrowHead(body, true);
    }

    @Override
    protected void buildHead1() {
        head1 = new RepeatArrowTail(body, false);
    }

    @Override
    protected void buildBody() {
        body = new RepeatArrowBody();
    }

}
