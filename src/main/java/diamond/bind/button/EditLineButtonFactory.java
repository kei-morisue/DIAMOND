/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button;

import java.awt.event.ActionListener;

import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.paint.ScreenUpdater;
import diamond.viewsetting.paint.uipanel.ChangeHint;

/**
 * @author long_
 *
 */
public abstract class EditLineButtonFactory extends PaintActionButtonFactory {

    @Override
    protected java.awt.event.KeyListener buildKeyListner() {
        return ScreenUpdater.getInstance().getKeyListener();
    }

    @Override
    protected ActionListener[] buildViewChangeListeners() {
        return new ActionListener[] {
                new ViewChangeListener(new ChangeHint(getLabelKey())),
                buildViewChangeListener() };
    }

    protected abstract ActionListener buildViewChangeListener();
}
