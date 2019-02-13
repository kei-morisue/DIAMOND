/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.resource.color;

import java.awt.Color;

import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class ColorStyle {
    static public final java.awt.Color PAINT_SCREEN_BG = java.awt.Color.white;
    static public final java.awt.Color MODEL_SCREEN_BG = java.awt.Color.white;

    static public final java.awt.Color ORIPOINT = java.awt.Color.black;
    public static final Color ORIPOINT_PICKED = Color.green;
    public static final Color ORIPOINT_POINTED = Color.green;

    static public final java.awt.Color ORIVERTEX = java.awt.Color.black;
    static public final java.awt.Color WRONG_ORIVERTEX = java.awt.Color.red;

    static public final java.awt.Color ORIFACE = java.awt.Color.gray;
    static public final java.awt.Color WRONG_ORI_FACE = java.awt.Color.red;
    public static final Color ORIFACE_FRONT = Color.gray;
    public static final Color ORIFACE_BACK = Color.white;

    final public static Color ORILINE_VALLEY = Color.BLUE;
    final public static Color ORILINE_MOUNTAIN = Color.RED;
    final public static Color ORILINE_AUX = Color.LIGHT_GRAY;
    final public static Color ORILINE_CUT = Color.black;
    final public static Color ORILINE_GRID = Color.LIGHT_GRAY;
    final public static Color ORILINE_CANDIDATE = Color.GREEN;
    final public static Color ORILINE_PICKED = Color.GREEN;
    final public static Color ORILINE_POINTED = Color.GREEN;

    final public static Color ORIHALFEDGE = Color.black;
    final public static Color ORIHALFEDGE_AUX = Color.black;

    static public final java.awt.Color DEBUGGING_STRING = java.awt.Color.black;

    public static Color getColor(OriLine l) {
        switch (l.getType()) {
        case MOUNTAIN:
            return ORILINE_MOUNTAIN;
        case VALLEY:
            return ORILINE_VALLEY;
        case CUT:
            return ORILINE_CUT;
        case AUX:
            return ORILINE_AUX;
        default:
            return ORILINE_AUX;
        }
    }

}
