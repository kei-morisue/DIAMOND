/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.controller.Context;
import diamond.view.ui.frame.PreviewFrame;
import diamond.view.ui.screen.TutorialScreen;

/**
 * @author Kei Morisue
 *
 */
public class TutorialAction implements ActionListener {
    private Context context;

    public TutorialAction(Context context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.saveTransform();
        new PreviewFrame(context, new TutorialScreen(context));
    }

}