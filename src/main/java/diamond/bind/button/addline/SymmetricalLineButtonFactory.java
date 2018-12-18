/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.addline;

import java.awt.event.ActionListener;

import diamond.bind.button.AddLineButtonFactory;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.symmetric.SymmetricalLineAction;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.main.uipanel.ChangeOnPaintInputButtonSelected;

/**
 * @author long_
 *
 */
public class SymmetricalLineButtonFactory extends AddLineButtonFactory {
    @Override
    protected String getLabelKey() {
        return StringID.SYMMETRIC_ID;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new SymmetricalLineAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnPaintInputButtonSelected());
    }

}
