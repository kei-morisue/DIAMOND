/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.style;

import java.awt.Color;

import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class VertexStyle {
    public static final Color NEUTRAL = Color.BLACK;
    public static final Color POINTED = Color.GREEN;
    public static final Color WRONG = Color.RED;

    public static Color getColor(Vertex v) {
        if (v.getProperty().isColored()) {
            return POINTED;
        }
        if (v.getProperty().isWrong) {
            return WRONG;
        }
        return NEUTRAL;
    }

    static private final double NEUTRAL_SIZE = 8.0;
    static private final double POINTED_SIZE = 16.0;
    static private final double WRONG_SIZE = 16.0;

    public static double getSize(Vertex vertex) {
        if (vertex.getProperty().isColored()) {
            return POINTED_SIZE;
        }
        if (vertex.getProperty().isWrong) {
            return WRONG_SIZE;
        }
        return NEUTRAL_SIZE;
    }
}
