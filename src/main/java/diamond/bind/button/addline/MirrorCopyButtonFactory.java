/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.addline;

import java.awt.event.ActionListener;

import diamond.bind.button.AddLineButtonFactory;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.core.PaintContext;
import diamond.paint.mirror.MirrorCopyAction;
import diamond.resource.string.StringID;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnPaintInputButtonSelected;

/**
 * @author long_
 *
 */
public class MirrorCopyButtonFactory extends AddLineButtonFactory {
    @Override
    protected String getLabelKey() {
        return StringID.MIRROR_ID;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new MirrorCopyAction(PaintContext.getInstance());
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnPaintInputButtonSelected());
    }

}
