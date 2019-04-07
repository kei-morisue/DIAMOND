/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.face;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;

/**
 * @author long_
 *
 */
public class OriFaceLiftingState extends AbstractPaintState {
    @Override
    protected void initialize() {
        setPrevClass(OriFace0PickkingState.class);
        setNextClass(this.getClass());
    }

    @Override
    protected void undoAction(PaintContext context) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    protected void onResult(PaintContext context) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        return true;
    }

}
