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

    final public static Color VALLEY = Color.BLUE;
    final public static Color MOUNTAIN = Color.RED;
    final public static Color CREASE = Color.LIGHT_GRAY;
    final public static Color UNSETTLED_VALLEY = Color.BLUE;
    final public static Color UNSETTLED_MOUNTAIN = Color.RED;
    final public static Color ORILINE_CUT = Color.black;
    final public static Color NONE = new Color(255, 0, 255);
    final public static Color PICKED = Color.GREEN;
    final public static Color POINTED = Color.GREEN;

    public static Color getCpColor(LineType lineType) {
        switch (lineType) {
        case MOUNTAIN:
            return MOUNTAIN;
        case VALLEY:
            return VALLEY;
        case CUT:
            return ORILINE_CUT;
        case CREASE:
            return CREASE;
        case UNSETTLED_MOUNTAIN:
            return UNSETTLED_MOUNTAIN;
        case UNSETTLED_VALLEY:
            return UNSETTLED_VALLEY;
        case NONE:
            return NONE;
        default:
            return CREASE;
        }
    }

}
