/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow;

import diamond.model.geom.element.diagram.arrow.body.SinkArrowBody;
import diamond.model.geom.element.diagram.arrow.head.EmptyArrowHead;
import diamond.model.geom.element.diagram.arrow.head.SinkArrowHead;

/**
 * @author long_
 *
 */
public class SinkArrow extends AbstractArrow {

    @Override
    protected void buildHead0() {
        head0 = new EmptyArrowHead(body, true);
    }

    @Override
    protected void buildHead1() {
        head1 = new SinkArrowHead(body, false);

    }

    @Override
    protected void buildBody() {
        body = new SinkArrowBody();
    }

}
