/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.addline;

import java.awt.event.ActionListener;

import diamond.bind.button.AddLineButtonFactory;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.byvalue.LineByValueAction;
import diamond.resource.string.StringKey;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnByValueButtonSelected;

/**
 * @author long_
 *
 */
public class LineByValueButtonFactory extends AddLineButtonFactory {
    @Override
    protected StringKey.LABEL getLabelKey() {
        return null;
    }

    @Override
    protected StringKey.HINT getHintKey() {
        return StringKey.HINT.BY_VALUE;
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
