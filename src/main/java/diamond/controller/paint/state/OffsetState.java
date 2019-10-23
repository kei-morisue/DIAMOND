/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;

/**
 * @author long_
 *
 */
public abstract class OffsetState extends OriPointPickkingState {

    protected Double getRotatedPoint(Context context, Double p) {
        Double rotated = new Double();
        AffineTransform transform = context.getPalette().getDiagram()
                .getTransform().getTransform();
        double theta = Math.atan2(-transform.getShearX(),
                transform.getScaleX());
        AffineTransform.getRotateInstance(-theta).transform(p, rotated);
        return rotated;
    }

}
