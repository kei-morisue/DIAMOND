/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.MouseEvent;

import diamond.controller.Context;
import diamond.view.ui.screen.FoldedScreen;

/**
 * @author Kei Morisue
 *
 */
public class FoldedScreenAction extends AbstractScreenAction {
    private Context context;

    public FoldedScreenAction(Context context, FoldedScreen screen) {
        super(screen);
        this.context = context;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

}
