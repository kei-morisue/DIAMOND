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
public class OriLineColor {

    final public static Color ORILINE_VALLEY = Color.BLUE;
    final public static Color ORILINE_MOUNTAIN = Color.RED;
    final public static Color ORILINE_AUX = Color.LIGHT_GRAY;
    final public static Color ORILINE_AUX_VALLEY = Color.BLUE;
    final public static Color ORILINE_AUX_MOUNTAIN = Color.RED;
    final public static Color ORILINE_CUT = Color.black;
    final public static Color ORILINE_PICKED = Color.GREEN;
    final public static Color ORILINE_POINTED = Color.GREEN;
    public static Color getCpColor(LineType lineType) {
        switch (lineType) {
        case MOUNTAIN:
            return ORILINE_MOUNTAIN;
        case VALLEY:
            return ORILINE_VALLEY;
        case CUT:
            return ORILINE_CUT;
        case CREASE:
            return ORILINE_AUX;
        case UNSETTLED_MOUNTAIN:
            return ORILINE_AUX_MOUNTAIN;
        case UNSETTLED_VALLEY:
            return ORILINE_AUX_VALLEY;
        default:
            return ORILINE_AUX;
        }
    }

}
