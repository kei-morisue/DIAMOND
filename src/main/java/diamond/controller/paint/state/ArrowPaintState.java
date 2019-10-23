/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public class ArrowPaintState extends OriLinePickkingState {
    private Class<? extends AbstractArrow> arrowClass;

    public ArrowPaintState(Class<? extends AbstractArrow> arrowClass) {
        this.arrowClass = arrowClass;
    }

    @Override
    protected void undoAction(Context context) {
        OriLine pointedOriLine = context.getPaintScreenContext()
                .getPointedElements().getOriLine();
        if (pointedOriLine != null) {
            AbstractArrow arrow = pointedOriLine.getArrow();
            if (arrow != null) {
                pointedOriLine.setArrow(null);
                pointedOriLine.getHe().setArrow(null);
            }
        }
        rebuild(context);
    }

    @Override
    protected void onResult(Context context) {
    }

    @Override
    protected void rebuild(Context context) {
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        OriLine pointedOriLine = context.getPaintScreenContext()
                .getPointedElements().getOriLine();
        if (pointedOriLine != null) {
            AbstractArrow arrow = pointedOriLine.getArrow();
            if (arrow != null) {
                arrow.flip();
            } else {
                try {
                    AbstractArrow newArrow = arrowClass.newInstance();
                    pointedOriLine
                            .setArrow(newArrow);
                    pointedOriLine.getHe().setArrow(newArrow);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        return false;
    }

    @Override
    protected void initialize() {
        setPrevClass(ArrowPaintState.class);
        setNextClass(ArrowPaintState.class);
    }

}
