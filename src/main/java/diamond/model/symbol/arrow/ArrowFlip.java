/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow;

import diamond.model.symbol.arrow.head.Empty;
import diamond.model.symbol.arrow.head.Flip;

/**
 * @author Kei Morisue
 *
 */
public class ArrowFlip extends AbstractArrow {

    @Override
    protected void buildHead() {
        arrowHead = new Flip();
    }

    @Override
    protected void buildTail() {
        arrowTail = new Empty();
    }

    @Override
    protected void buildBody() {
        body = new diamond.model.symbol.arrow.body.Flip();
    }
}
