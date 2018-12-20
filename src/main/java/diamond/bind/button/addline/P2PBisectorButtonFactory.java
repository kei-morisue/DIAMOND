/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.addline;

import java.awt.event.ActionListener;

import diamond.bind.button.AddLineButtonFactory;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.pbisec.TwoPointBisectorAction;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnPaintInputButtonSelected;

/**
 * @author long_
 *
 */
public class P2PBisectorButtonFactory extends AddLineButtonFactory {
    @Override
    protected String getLabelKey() {
        return StringID.PERPENDICULAR_BISECTOR_ID;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new TwoPointBisectorAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnPaintInputButtonSelected());
    }

}
