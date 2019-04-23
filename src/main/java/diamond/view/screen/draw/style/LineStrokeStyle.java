package diamond.view.screen.draw.style;

import java.awt.BasicStroke;

import diamond.model.geom.element.LineType;

public class LineStrokeStyle {

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
    final public static BasicStroke STROKE_AUX = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_RADAR = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_OFFSET = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final public static float DASH_VALLEY[] = { 10.0f, 3.0f };
    final public static float DASH_MOUNTAIN[] = { 10.0f, 2.0f, 2.0f, 2.0f };

    final public static BasicStroke STROKE_AUX_VALLEY = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
            10.0f, DASH_VALLEY, 0.0f);
    final public static BasicStroke STROKE_AUX_MOUNTAIN = new BasicStroke(2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
            10.0f, DASH_MOUNTAIN, 0.0f);

    final public static BasicStroke STROKE_TEMPORARY = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final public static BasicStroke STROKE_EDGE = new BasicStroke(2.0f,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_CREASE = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final public static BasicStroke STROKE_ARROW = new BasicStroke(2.0f,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

    final static float dash[] = { 3.0f };

    final public static BasicStroke STROKE_SELECT_BY_AREA = new BasicStroke(
            0.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
            0.0f);

    // Editing outlines (?)
    final public static BasicStroke STROKE_TMP_OUTLINE = new BasicStroke(3.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke MODEL_STROKE_CUT = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    public static BasicStroke getCpStroke(LineType lineType) {
        switch (lineType) {
        case AUX:
            return STROKE_AUX;
        case AUX_VALLEY:
            return STROKE_AUX_VALLEY;
        case AUX_MOUNTAIN:
            return STROKE_AUX_MOUNTAIN;
        default:
            return STROKE_MOUNTAIN;
        }
    }

    public static BasicStroke getDiagramStroke(LineType lineType) {
        switch (lineType) {
        case AUX:
            return STROKE_AUX;
        case AUX_VALLEY:
            return STROKE_AUX_VALLEY;
        case AUX_MOUNTAIN:
            return STROKE_AUX_MOUNTAIN;
        default:
            return STROKE_EDGE;
        }
    }
}
