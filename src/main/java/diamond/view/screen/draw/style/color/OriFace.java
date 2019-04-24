/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw.style.color;

import java.awt.Color;

import diamond.view.screen.draw.style.FaceStyle;

/**
 * @author long_
 *
 */
public class OriFace {

    public static final Color ORI_FACE_PICKED = Color.green;
    public static final Color ORI_FACE_POINTED = Color.green;
    public static Color ORI_FACE_FRONT = Color.gray;
    public static Color ORI_FACE_BACK = Color.white;

    public static Color getBaseFaceColor() {
        return (FaceStyle.START_FROM_FRONT_FACE) ? OriFace.ORI_FACE_FRONT
                : ORI_FACE_BACK;//TODO
    }

    public static Color getColor(boolean isFaceFront) {
        return (isFaceFront)
                ? ORI_FACE_FRONT
                : ORI_FACE_BACK;//TODO
    }

}
