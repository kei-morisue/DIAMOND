/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.editline;

import java.awt.event.ActionListener;

import diamond.bind.button.EditLineButtonFactory;
import diamond.bind.state.ErrorListener;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.copypaste.CopyAndPasteActionWrapper;
import diamond.paint.copypaste.CopyPasteErrorListener;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.main.uipanel.ChangeOnSelectButtonSelected;

/**
 * @author long_
 *
 */
public class CutPasteButtonFactory extends EditLineButtonFactory {
    @Override
    protected String getLabelKey() {
        return StringID.CUT_PASTE_ID;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return new CopyPasteErrorListener();
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new CopyAndPasteActionWrapper(true);//TODO weird wrapper there
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnSelectButtonSelected());
    }

}
