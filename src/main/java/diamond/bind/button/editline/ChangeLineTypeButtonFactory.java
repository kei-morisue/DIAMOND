/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.editline;

import java.awt.event.ActionListener;

import diamond.bind.button.EditLineButtonFactory;
import diamond.bind.state.ErrorListener;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.linetype.ChangeLineTypeAction;
import diamond.resource.string.StringKey;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnAlterTypeButtonSelected;

/**
 * @author long_
 *
 */
public class ChangeLineTypeButtonFactory extends EditLineButtonFactory {
    @Override
    protected StringKey.LABEL getLabelKey() {
        return StringKey.LABEL.CHANGE_LINE_TYPE;
    }

    @Override
    protected StringKey.HINT getHintKey() {
        return StringKey.HINT.CHANGE_LINE_TYPE;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new ChangeLineTypeAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnAlterTypeButtonSelected());
    }

}
