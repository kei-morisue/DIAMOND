/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.style;

import java.awt.Color;

import diamond.model.cyborg.Face;

/**
 * @author Kei Morisue
 *
 */
public class FaceStyle {
    public static final Color COLOR_POINTED = Color.green;
    public static Color COLOR_FRONT = Color.gray;
    public static Color COLOR_BACK = Color.white;

    public static Color getColor(Face face) {
        if (face.getProperty().isColored()) {
            return COLOR_POINTED;
        }
        return (face.isFaceFront()) ? COLOR_FRONT : COLOR_BACK;
    }

    public static double CP_FACE_SCALE = 0.7;
}
