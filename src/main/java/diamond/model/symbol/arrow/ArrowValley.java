/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow;

import diamond.model.symbol.arrow.body.BodyCurved;
import diamond.model.symbol.arrow.head.HeadEmpty;
import diamond.model.symbol.arrow.head.HeadValley;

/**
 * @author Kei Morisue
 *
 */
public class ArrowValley extends AbstractArrow {

    @Override
    protected void buildHead() {
        arrowHead = new HeadValley();
    }

    @Override
    protected void buildTail() {
        arrowTail = new HeadEmpty();
    }

    @Override
    protected void buildBody() {
        body = new BodyCurved();
    }
}
