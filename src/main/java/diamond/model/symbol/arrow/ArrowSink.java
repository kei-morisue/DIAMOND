/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow;

import diamond.model.symbol.arrow.head.Empty;
import diamond.model.symbol.arrow.head.Sink;

/**
 * @author Kei Morisue
 *
 */
public class ArrowSink extends AbstractArrow {

    public ArrowSink() {
        super();
        scale = 0.5;
    }

    @Override
    protected void buildHead() {
        arrowHead = new Sink();
    }

    @Override
    protected void buildTail() {
        arrowTail = new Empty();
    }

    @Override
    protected void buildBody() {
        body = new diamond.model.symbol.arrow.body.Sink();
    }
}
