/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.editline;

import java.awt.event.ActionListener;

import diamond.bind.button.EditLineButtonFactory;
import diamond.bind.state.ErrorListener;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.deletevertex.DeleteVertexAction;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnOtherCommandButtonSelected;

/**
 * @author long_
 *
 */
public class DeleteVertexButtonFactory extends EditLineButtonFactory {
    @Override
    protected String getLabelKey() {
        return StringID.DELETE_VERTEX_ID;
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new DeleteVertexAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnOtherCommandButtonSelected());
    }

}
