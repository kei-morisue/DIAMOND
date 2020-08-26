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
    private static Color front = Color.gray;
    private static Color back = Color.white;

    public FaceStyle() {
    }

    public static Color getFoldedColor(Face face) {
        if (face.getProperty().isColored()) {
            return COLOR_POINTED;
        }
        return (face.isFaceFront()) ? front : back;
    }

    public static Color getCpColor(Face face) {
        if (face.getProperty().isColored()) {
            return COLOR_POINTED;
        }
        return back;
    }

    public static double CP_FACE_SCALE = 0.7;

    @Deprecated
    public Color getFront() {
        return front;
    }

    @Deprecated
    public void setFront(Color cOLOR_FRONT) {
        front = cOLOR_FRONT;
    }

    @Deprecated
    public Color getBack() {
        return back;
    }

    @Deprecated
    public void setBack(Color cOLOR_BACK) {
        back = cOLOR_BACK;
    }
}
