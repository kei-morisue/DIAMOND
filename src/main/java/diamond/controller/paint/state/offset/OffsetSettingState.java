/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offset;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.palette.cp.editor.OffsetSetter;

/**
 * @author long_
 *
 */
public class OffsetSettingState extends AbstractPaintState {
    @Override
    protected void initialize() {
        setPrevClass(OffsetSettingState.class);
        setNextClass(OffsetSettingState.class);
    }

    @Override
    protected void undoAction(PaintContext context) {
        Set<OriVertex> vertices = context.getPalette().getOriModel()
                .getVertices();
        Cp cp = context.getPalette().getCP();
        OffsetSetter.reset(vertices, cp);
        context.getPalette().getOriModel().fold();
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.getPalette().getOriModel().fold();
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        Double p = context.getCurrentLogicalMousePoint();
        if (p != null) {
            Set<OriVertex> vertices = context.getPalette().getOriModel()
                    .getVertices();
            Cp cp = context.getPalette().getCP();
            Double rotated = getRotatedPoint(context, p);
            OffsetSetter.set(cp, rotated, vertices);
            return true;
        }
        return false;
    }

    private Double getRotatedPoint(PaintContext context, Double p) {
        Double rotated = new Double();
        AffineTransform transform = context.getPalette().getDiagram()
                .getTransform().getTransform();
        double theta = Math.atan2(-transform.getShearX(),
                transform.getScaleX());
        AffineTransform.getRotateInstance(-theta).transform(p, rotated);
        return rotated;
    }

}
