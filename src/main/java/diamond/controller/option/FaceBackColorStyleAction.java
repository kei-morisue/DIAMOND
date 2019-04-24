/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.option;

import java.awt.Color;
import java.awt.Component;

import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.draw.style.color.OriFace;

/**
 * @author long_
 *
 */
public class FaceBackColorStyleAction<T extends Component>
        extends ColorStyleAction<T> {
    @Override
    protected String getTitle() {
        return ResourceHolder
                .getLabelString(LABEL.FACE_BACK_COLOR);
    }

    @Override
    protected Color getColorStyle() {
        return OriFace.ORI_FACE_BACK;
    }

    @Override
    protected void setColorStyle(Color color) {
        OriFace.ORI_FACE_BACK = color;
    }

}
