/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import diamond.model.geom.element.diagram.arrow.AbstractArrow;
import diamond.model.geom.element.diagram.arrow.FoldUnfoldArrow;

/**
 * @author long_
 *
 */
public class FoldUnfoldArrowPaintState extends ArrowPaintState {

    @Override
    protected void initialize() {
        setNextClass(FoldUnfoldArrowPaintState.class);
        setPrevClass(FoldUnfoldArrowPaintState.class);
    }

    @Override
    protected AbstractArrow buildArrow() {
        return new FoldUnfoldArrow();
    }

}
