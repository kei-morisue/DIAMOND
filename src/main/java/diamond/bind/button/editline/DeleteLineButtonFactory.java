/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.editline;

import java.awt.event.ActionListener;

import diamond.bind.button.EditLineButtonFactory;
import diamond.bind.state.ErrorListener;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.deleteline.DeleteLineAction;
import diamond.resource.string.StringKey;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnOtherCommandButtonSelected;

/**
 * @author long_
 *
 */
public class DeleteLineButtonFactory extends EditLineButtonFactory {
    @Override
    protected StringKey.LABEL getLabelKey() {
        return StringKey.LABEL.DELETE_LINE;
    }

    @Override
    protected StringKey.HINT getHintKey() {
        return StringKey.HINT.DELETE_LINE;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new DeleteLineAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnOtherCommandButtonSelected());
    }

}
