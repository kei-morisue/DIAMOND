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
public class ValleyFoldArrowAddingState extends ArrowAddingState {

    @Override
    protected void initialize() {
        setNextClass(ValleyFoldArrowAddingState.class);
        setPrevClass(ValleyFoldArrowAddingState.class);
    }

    @Override
    protected AbstractArrow buildArrow() {
        return new ValleyFoldArrow();
    }

}
