/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public class ArrowPaintState extends AbstractPaintState {
    private Class<? extends AbstractArrow> arrowClass;

    public ArrowPaintState(Class<? extends AbstractArrow> arrowClass) {
        this.arrowClass = arrowClass;
    }

    @Override
    protected void undoAction(PaintContext context) {
        OriLine pointedOriLine = context.getPointedOriLine();
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
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriLine pointedOriLine = context.getPointedOriLine();
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
