/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.bind.button;

import java.awt.event.ActionListener;

import diamond.bind.state.ErrorListener;
import diamond.viewsetting.ViewChangeListener;
import diamond.viewsetting.main.ScreenUpdater;
import diamond.viewsetting.main.uipanel.ChangeHint;

/**
 * @author long_
 *
 */
public abstract class AddLineButtonFactory extends PaintActionButtonFactory {

    @Override
    protected java.awt.event.KeyListener buildKeyListner() {
        return ScreenUpdater.getInstance().getKeyListener();
    }

    @Override
    protected ErrorListener buildErrorListener() {
        return null;
    }

    @Override
    protected ActionListener[] buildViewChangeListeners() {
        return new ActionListener[] {
                new ViewChangeListener(new ChangeHint(getLabelKey())),
                buildViewChangeListener() };
    }

    protected abstract ActionListener buildViewChangeListener();
}
