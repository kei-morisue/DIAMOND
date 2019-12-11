/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.geom.Point2D.Double;

import diamond.controller.Context;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.CenterPointUtil;

/**
 * @author Kei Morisue
 *
 */
public final class OffsetSymbolScreen extends AbstractOffsetScreen {

    public OffsetSymbolScreen(Context context) {
        super(context);
    }

    @Override
    public Double getCenterPoint(Context context) {
        HalfEdge he = context.getPicker().getHalfEdges().get(0);
        return CenterPointUtil.get(he);
    }
}
