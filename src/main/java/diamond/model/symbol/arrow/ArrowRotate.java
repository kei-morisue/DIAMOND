/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow;

import diamond.model.symbol.arrow.body.Rotate;
import diamond.model.symbol.arrow.head.Valley;

/**
 * @author Kei Morisue
 *
 */
public class ArrowRotate extends AbstractArrow {

    @Override
    protected void buildHead() {
        arrowHead = new Valley();
    }

    @Override
    protected void buildTail() {
        arrowTail = new Valley();
    }

    @Override
    protected void buildBody() {
        body = new Rotate();
    }
}
