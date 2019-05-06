/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import diamond.model.geom.element.diagram.arrow.AbstractArrow;
import diamond.model.geom.element.diagram.arrow.ValleyFoldArrow;

/**
 * @author long_
 *
 */
public class ValleyArrowPaintState extends ArrowPaintState {

    @Override
    protected void initialize() {
        setNextClass(ValleyArrowPaintState.class);
        setPrevClass(ValleyArrowPaintState.class);
    }

    @Override
    protected AbstractArrow buildArrow() {
        return new ValleyFoldArrow();
    }

}
