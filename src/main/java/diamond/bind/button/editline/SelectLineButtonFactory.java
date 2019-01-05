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
import diamond.paint.selectline.SelectLineAction;
import diamond.resource.string.StringKey.HINT;
import diamond.resource.string.StringKey.LABEL;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnSelectButtonSelected;

/**
 * @author long_
 *
 */
public class SelectLineButtonFactory extends EditLineButtonFactory {
    @Override
    protected LABEL getLabelKey() {
        return LABEL.SELECT;
    }

    @Override
    protected HINT getHintKey() {
        return HINT.SELECT;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new SelectLineAction(PaintContext.getInstance());
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnSelectButtonSelected());
    }

}
