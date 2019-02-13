package diamond.view.resource.graphic;

import java.awt.BasicStroke;

public class LineStrokeSetting {

    final public static BasicStroke STROKE_CUT = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_VALLEY = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_RIDGE = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_PICKED = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_POINTED = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_GRID = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_AUX = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_MOVING = new BasicStroke(0.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final public static BasicStroke STROKE_EDGE = new BasicStroke(2.0f,
            BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_CREASE = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    final static float dash[] = { 3.0f };

    final public static BasicStroke STROKE_SELECT_BY_AREA = new BasicStroke(
            0.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
            0.0f);

    // Editing outlines (?)
    final public static BasicStroke STROKE_TMP_OUTLINE = new BasicStroke(3.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke MODEL_STROKE_CUT = new BasicStroke(1.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

}
