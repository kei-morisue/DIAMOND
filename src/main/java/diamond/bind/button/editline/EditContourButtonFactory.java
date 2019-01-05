/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.editline;

import java.awt.event.ActionListener;

import diamond.bind.button.EditLineButtonFactory;
import diamond.bind.state.ErrorListener;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.editcontour.EditContourAction;
import diamond.resource.string.StringKey;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnOtherCommandButtonSelected;

/**
 * @author long_
 *
 */
public class EditContourButtonFactory extends EditLineButtonFactory {
    @Override
    protected StringKey.LABEL getLabelKey() {
        return StringKey.LABEL.EDIT_CONTOUR;
    }

    @Override
    protected StringKey.HINT getHintKey() {
        return StringKey.HINT.EDIT_CONTOUR;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new EditContourAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnOtherCommandButtonSelected());
    }

}
