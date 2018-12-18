/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.addline;

import java.awt.event.ActionListener;

import diamond.bind.button.AddLineButtonFactory;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.byvalue.LineByValueAction;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.main.uipanel.ChangeOnByValueButtonSelected;

/**
 * @author long_
 *
 */
public class LineByValueButtonFactory extends AddLineButtonFactory {
    @Override
    protected String getLabelKey() {
        return StringID.BY_VALUE_ID;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new LineByValueAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnByValueButtonSelected());
    }

}
