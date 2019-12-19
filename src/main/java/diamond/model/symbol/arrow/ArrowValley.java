/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow;

import diamond.model.symbol.arrow.body.Curved;
import diamond.model.symbol.arrow.head.Empty;
import diamond.model.symbol.arrow.head.Valley;

/**
 * @author Kei Morisue
 *
 */
public class ArrowValley extends AbstractArrow {

    @Override
    protected void buildHead() {
        arrowHead = new Valley();
    }

    @Override
    protected void buildTail() {
        arrowTail = new Empty();
    }

    @Override
    protected void buildBody() {
        body = new Curved(-0.25);
    }
}
