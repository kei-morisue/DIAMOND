/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow;

import diamond.model.geom.element.diagram.arrow.body.RotateArrowBody;
import diamond.model.geom.element.diagram.arrow.head.ValleyFoldArrowHead;

/**
 * @author long_
 *
 */
public class RotateArrow extends AbstractArrow {

    @Override
    protected void buildHead0() {
        head0 = new ValleyFoldArrowHead(body, true);
    }

    @Override
    protected void buildHead1() {
        head1 = new ValleyFoldArrowHead(body, false);

    }

    @Override
    protected void buildBody() {
        body = new RotateArrowBody();
    }

}
