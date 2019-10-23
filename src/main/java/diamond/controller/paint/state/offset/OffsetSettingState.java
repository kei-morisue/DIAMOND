/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offset;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.Palette;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.palette.cp.editor.OffsetSetter;

/**
 * @author long_
 *
 */
public class OffsetSettingState extends OriPointPickkingState {
    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);
    }

    @Override
    protected void undoAction(Context context) {
        Set<OriVertex> vertices = context.getPalette().getOriModel()
                .getVertices();
        Cp cp = context.getPalette().getCP();
        OffsetSetter.reset(vertices, cp);
        context.initialize();
        context.getPalette().getOriModel().fold();
    }

    @Override
    protected void onResult(Context context) {
        Palette palette = context.getPalette();
        OriModel oriModel = palette.getOriModel();
        Set<OriVertex> vertices = oriModel.getVertices();
        for (OriVertex v : vertices) {
            v.setPickked(false);
        }
        context.initialize();
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getOriModel().fold();
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        return true;
    }

    @Override
    public void setCandate(Context context) {
        super.setCandate(context);
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Double p = paintScreenContext.getCurrentLogicalMousePoint();
        if (p != null) {
            Set<OriVertex> vertices = context.getPalette().getOriModel()
                    .getVertices();
            Cp cp = context.getPalette().getCP();
            Double rotated = getRotatedPoint(context, p);
            OffsetSetter.set(cp, rotated, vertices);
            rebuild(context);
        }
    }

    private Double getRotatedPoint(Context context, Double p) {
        Double rotated = new Double();
        AffineTransform transform = context.getPalette().getDiagram()
                .getTransform().getTransform();
        double theta = Math.atan2(-transform.getShearX(),
                transform.getScaleX());
        AffineTransform.getRotateInstance(-theta).transform(p, rotated);
        return rotated;
    }

}
