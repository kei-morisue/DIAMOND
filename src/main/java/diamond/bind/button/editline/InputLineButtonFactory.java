/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.editline;

import java.awt.event.ActionListener;

import diamond.bind.button.EditLineButtonFactory;
import diamond.bind.state.ErrorListener;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.util.LazyMouseAction;
import diamond.resource.string.StringKey;
import diamond.resource.string.StringKey.HINT;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnPaintInputButtonSelected;

/**
 * @author long_
 *
 */
public class InputLineButtonFactory extends EditLineButtonFactory {
    @Override
    protected StringKey.LABEL getLabelKey() {
        return StringKey.LABEL.INPUT_LINE;
    }

    @Override
    protected StringKey.HINT getHintKey() {
        return HINT.INPUT_LINE;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new LazyMouseAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnPaintInputButtonSelected());
    }

}
