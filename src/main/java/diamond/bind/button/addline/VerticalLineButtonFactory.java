/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.addline;

import java.awt.event.ActionListener;

import diamond.bind.button.AddLineButtonFactory;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.vertical.VerticalLineAction;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnPaintInputButtonSelected;

/**
 * @author long_
 *
 */
public class VerticalLineButtonFactory extends AddLineButtonFactory {
    @Override
    protected String getLabelKey() {
        return StringID.VERTICAL_ID;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new VerticalLineAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnPaintInputButtonSelected());
    }

}
