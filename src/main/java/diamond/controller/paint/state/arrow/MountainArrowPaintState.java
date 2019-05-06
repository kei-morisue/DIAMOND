/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import diamond.model.geom.element.diagram.arrow.AbstractArrow;
import diamond.model.geom.element.diagram.arrow.MountainFoldArrow;

/**
 * @author long_
 *
 */
public class MountainArrowPaintState extends ArrowPaintState {

    @Override
    protected void initialize() {
        setNextClass(MountainArrowPaintState.class);
        setPrevClass(MountainArrowPaintState.class);
    }

    @Override
    protected AbstractArrow buildArrow() {
        return new MountainFoldArrow();
    }

}
