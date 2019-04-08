/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.option;

import java.awt.Color;
import java.awt.Component;

import diamond.view.paint.screen.draw.style.ColorStyle;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class FaceBackColorStyleAction<T extends Component>
        extends ColorStyleAction<T> {
    @Override
    protected String getTitle() {
        return ResourceHolder
                .getLabelString(LABEL.FACE_BACK_STYLE);
    }

    @Override
    protected Color getColorStyle() {
        return ColorStyle.ORI_FACE_BACK;
    }

    @Override
    protected void setColorStyle(Color color) {
        ColorStyle.ORI_FACE_BACK = color;
    }

}
