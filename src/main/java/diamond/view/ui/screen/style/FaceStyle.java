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

    public FaceStyle() {
    }

    public static Color getFoldedColor(Face face) {
        if (face.getProperty().isColored()) {
            return COLOR_POINTED;
        }
        return (face.isFaceFront()) ? COLOR_FRONT : COLOR_BACK;
    }

    public static Color getCpColor(Face face) {
        if (face.getProperty().isColored()) {
            return COLOR_POINTED;
        }
        return COLOR_BACK;
    }

    public static double CP_FACE_SCALE = 0.7;

    @Deprecated
    public static Color getCOLOR_FRONT() {
        return COLOR_FRONT;
    }

    @Deprecated
    public static void setCOLOR_FRONT(Color cOLOR_FRONT) {
        COLOR_FRONT = cOLOR_FRONT;
    }

    @Deprecated
    public static Color getCOLOR_BACK() {
        return COLOR_BACK;
    }

    @Deprecated
    public static void setCOLOR_BACK(Color cOLOR_BACK) {
        COLOR_BACK = cOLOR_BACK;
    }
}
