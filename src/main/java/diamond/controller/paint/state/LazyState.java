/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class LazyState extends AbstractPaintState {

    public LazyState() {
        super();
        initialize();
    }

    @Override
    protected void initialize() {
        setPrevClass(LazyState.class);
        setNextClass(LazyState.class);
    }

    @Override
    protected void undoAction(PaintContext context) {
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        return false;
    }

}
