/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.geom.Point2D.Double;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public final class OffsetVertexScreen extends AbstractOffsetScreen {

    public OffsetVertexScreen(Context context) {
        super(context);
    }

    @Override
    public Double getCenterPoint(Context context) {
        return context.getPicker().getVertices().get(0).getFolded();
    }
}
