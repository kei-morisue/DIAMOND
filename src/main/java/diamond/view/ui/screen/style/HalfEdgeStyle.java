/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.style;

import java.awt.BasicStroke;
import java.awt.Color;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeStyle {
    final public static Color VALLEY = Color.BLUE;
    final public static Color MOUNTAIN = Color.RED;
    final public static Color CREASE = Color.LIGHT_GRAY;
    final public static Color UNSETTLED_VALLEY = Color.BLUE;
    final public static Color UNSETTLED_MOUNTAIN = Color.RED;
    final public static Color ORILINE_CUT = Color.black;
    final public static Color NONE = new Color(255, 0, 255);
    final public static Color POINTED = Color.GREEN;

    public static Color getColor(HalfEdge he) {
        EdgeType type = he.getType();
        if (he.getProperty().isPointed()) {
            return POINTED;
        }
        switch (type) {
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

    final public static BasicStroke STROKE_CUT = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_VALLEY = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_MOUNTAIN = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_PICKED = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_POINTED = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_GRID = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_CREASE = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_NONE = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final public static BasicStroke STROKE_RADAR = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_OFFSET = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final public static BasicStroke SINK_ARROW_OUTLINE = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final public static float DASH_VALLEY[] = { 10.0f, 3.0f };
    final public static float DASH_MOUNTAIN[] = { 10.0f, 2.0f, 2.0f, 2.0f };
    final public static BasicStroke STROKE_UNSETTLED_VALLEY = new BasicStroke(
            2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
            10.0f, DASH_VALLEY, 0.0f);
    final public static BasicStroke STROKE_UNSETTLED_MOUNTAIN = new BasicStroke(
            2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
            10.0f, DASH_MOUNTAIN, 0.0f);

    final public static BasicStroke STROKE_EDGE = new BasicStroke(2.0f,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_ARROW = new BasicStroke(2.0f,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_FLIP_ARROW = new BasicStroke(3.0f,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

    final public static BasicStroke STROKE_TEMPORARY = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final static float dash[] = { 3.0f };
    final public static BasicStroke STROKE_TMP_OUTLINE = new BasicStroke(3.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    public static BasicStroke getCpStroke(EdgeType type) {
        switch (type) {
        case CREASE:
            return STROKE_CREASE;
        case UNSETTLED_VALLEY:
            return STROKE_UNSETTLED_VALLEY;
        case UNSETTLED_MOUNTAIN:
            return STROKE_UNSETTLED_MOUNTAIN;
        case NONE:
            return STROKE_NONE;
        default:
            return STROKE_MOUNTAIN;
        }
    }

    public static BasicStroke getModelStroke(EdgeType type) {
        switch (type) {
        case CREASE:
            return STROKE_CREASE;
        case UNSETTLED_VALLEY:
            return STROKE_UNSETTLED_VALLEY;
        case UNSETTLED_MOUNTAIN:
            return STROKE_UNSETTLED_MOUNTAIN;
        default:
            return STROKE_EDGE;
        }
    }
}
