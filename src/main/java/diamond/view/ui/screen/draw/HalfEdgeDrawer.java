/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Shape;
import java.awt.geom.Line2D;

import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeDrawer {

    public static Shape buildLine(HalfEdge he) {
        return new Line2D.Double(
                he.getV0().x,
                he.getV0().y,
                he.getV1().x,
                he.getV1().y);
    }

}
