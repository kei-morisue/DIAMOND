/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.editline;

import java.awt.event.ActionListener;

import diamond.bind.button.EditLineButtonFactory;
import diamond.bind.state.ErrorListener;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.core.PaintContext;
import diamond.paint.selectline.SelectAllLineAction;
import diamond.resource.string.StringKey.HINT;
import diamond.resource.string.StringKey.LABEL;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnSelectButtonSelected;

/**
 * @author long_
 *
 */
public class SelectAllLineButtonFactory extends EditLineButtonFactory {
    @Override
    protected LABEL getLabelKey() {
        return LABEL.SELECT_ALL_LINES;
    }

    @Override
    protected HINT getHintKey() {
        return HINT.SELECT_ALL_LINES;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new SelectAllLineAction(PaintContext.getInstance());
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnSelectButtonSelected());
    }
}
