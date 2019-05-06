/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.option;

import java.awt.Color;
import java.awt.Component;

import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.draw.style.color.OriFaceColor;

/**
 * @author long_
 *
 */
public class FaceFrontColorStyleAction<T extends Component>
        extends ColorStyleAction<T> {
    @Override
    protected String getTitle() {
        return ResourceHolder
                .getLabelString(LABEL.FACE_FRONT_COLOR);
    }

    @Override
    protected Color getColorStyle() {
        return OriFaceColor.ORI_FACE_FRONT;
    }

    @Override
    protected void setColorStyle(Color color) {
        OriFaceColor.ORI_FACE_FRONT = color;
    }

}
