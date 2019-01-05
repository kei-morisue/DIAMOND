/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button.addline;

import java.awt.event.ActionListener;

import diamond.bind.button.AddLineButtonFactory;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.segment.TwoPointSegmentAction;
import diamond.resource.string.StringKey;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.uipanel.ChangeOnPaintInputButtonSelected;

/**
 * @author long_
 *
 */
public class P2PSegmentButtonFactory extends AddLineButtonFactory {
    @Override
    protected StringKey.LABEL getLabelKey() {
        return null;
    }

    @Override
    protected StringKey.HINT getHintKey() {
        return StringKey.HINT.DIRECT_V;
    }

    @Override
    protected GraphicMouseActionInterface buildMouseAction() {
        return new TwoPointSegmentAction();
    }

    @Override
    protected ActionListener buildViewChangeListener() {
        return new ViewChangeListener(
                new ChangeOnPaintInputButtonSelected());
    }

}
