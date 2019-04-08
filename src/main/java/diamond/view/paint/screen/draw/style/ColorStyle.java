/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen.draw.style;

import java.awt.Color;

import diamond.model.geom.element.LineType;

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
    public static final Color ORIPOINT_ORIGIN = Color.yellow;

    static public final java.awt.Color ORIVERTEX = java.awt.Color.black;
    static public final java.awt.Color WRONG_ORIVERTEX = java.awt.Color.red;

    static public final java.awt.Color ORIFACE = java.awt.Color.gray;
    static public final java.awt.Color WRONG_ORI_FACE = java.awt.Color.red;
    public static Color ORI_FACE_FRONT = Color.gray;
    public static Color ORI_FACE_BACK = Color.white;
    public static final Color ORI_FACE_PICKED = Color.green;
    public static final Color ORI_FACE_POINTED = Color.green;

    final public static Color ORILINE_VALLEY = Color.BLUE;
    final public static Color ORILINE_MOUNTAIN = Color.RED;
    final public static Color ORILINE_AUX = Color.LIGHT_GRAY;
    final public static Color ORILINE_AUX_VALLEY = Color.BLUE;
    final public static Color ORILINE_AUX_MOUNTAIN = Color.RED;
    final public static Color ORILINE_CUT = Color.black;
    final public static Color ORILINE_GRID = Color.LIGHT_GRAY;
    final public static Color ORILINE_CANDIDATE = Color.GREEN;
    final public static Color ORILINE_PICKED = Color.GREEN;
    final public static Color ORILINE_POINTED = Color.GREEN;

    final public static Color ORI_HALFEDGE = Color.black;
    final public static Color ORI_HALFEDGE_AUX = Color.black;
    final public static Color ORI_HALFEDGE_AUX_MOUNTAIN = Color.RED;
    final public static Color ORI_HALFEDGE_AUX_VALLEY = Color.BLUE;

    final public static Color ARROW_BODY = Color.black;
    final public static Color ARROW_VALLEY = Color.black;
    final public static Color ARROW_MOUNTAIN = Color.white;

    static public final java.awt.Color DEBUGGING_STRING = java.awt.Color.black;

    static public final java.awt.Color STEP_NO = java.awt.Color.black;

    public static Color getCpColor(LineType lineType) {
        switch (lineType) {
        case MOUNTAIN:
            return ORILINE_MOUNTAIN;
        case VALLEY:
            return ORILINE_VALLEY;
        case CUT:
            return ORILINE_CUT;
        case AUX:
            return ORILINE_AUX;
        case AUX_MOUNTAIN:
            return ORILINE_AUX_MOUNTAIN;
        case AUX_VALLEY:
            return ORILINE_AUX_VALLEY;
        default:
            return ORILINE_AUX;
        }
    }

    public static Color getDiagramColor(LineType lineType) {
        switch (lineType) {
        case CUT:
            return ORILINE_CUT;
        case AUX:
            return ORI_HALFEDGE_AUX;
        case AUX_MOUNTAIN:
            return ORI_HALFEDGE_AUX_MOUNTAIN;
        case AUX_VALLEY:
            return ORI_HALFEDGE_AUX_VALLEY;
        default:
            return ORI_HALFEDGE;
        }
    }

}
