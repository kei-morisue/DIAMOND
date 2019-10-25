/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.repeatarrow;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PointedElement;
import diamond.controller.paint.state.ArrowPaintState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;
import diamond.model.geom.element.diagram.arrow.RepeatArrow;
import diamond.view.ui.panel.DiagramsSettingDialog;

/**
 * @author long_
 *
 */
public class RepeatArrowPaintState extends ArrowPaintState {

    public RepeatArrowPaintState(Class<? extends AbstractArrow> arrowClass) {
        super(arrowClass);
    }

    @Override
    protected void initialize() {
        setPrevClass(RepeatArrowPaintState.class);
        setNextClass(RepeatArrowPaintState.class);
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriLine pointedOriLine = pointedElements.getOriLine();
        if (pointedOriLine != null) {
            AbstractArrow arrow = pointedOriLine.getArrow();
            if (arrow != null) {
                arrow.flip();
            } else {
                DiagramsSettingDialog dialog = new DiagramsSettingDialog(
                        context);
                dialog.showDialog();
                AbstractArrow newArrow = new RepeatArrow();
                pointedOriLine.setArrow(newArrow);
                pointedOriLine.getHe().setArrow(newArrow);

            }
            return true;
        }
        return false;
    }

    @Override
    protected void onResult(Context context) {
        context.initialize();
    }

}
