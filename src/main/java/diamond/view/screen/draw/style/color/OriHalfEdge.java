/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw.style.color;

import java.awt.Color;

import diamond.model.geom.element.LineType;

/**
 * @author long_
 *
 */
public class OriHalfEdge {

    final public static Color ORI_HALFEDGE = Color.black;
    final public static Color ORI_HALFEDGE_AUX = Color.black;
    final public static Color ORI_HALFEDGE_AUX_MOUNTAIN = Color.RED;
    final public static Color ORI_HALFEDGE_AUX_VALLEY = Color.BLUE;
    public static Color getDiagramColor(LineType lineType) {
        switch (lineType) {
        case CUT:
            return OriLine.ORILINE_CUT;
        case CREASE:
            return ORI_HALFEDGE_AUX;
        case UNSETTLED_MOUNTAIN:
            return ORI_HALFEDGE_AUX_MOUNTAIN;
        case UNSETTLED_VALLEY:
            return ORI_HALFEDGE_AUX_VALLEY;
        default:
            return ORI_HALFEDGE;
        }
    }

}
