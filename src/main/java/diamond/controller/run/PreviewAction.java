/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.controller.paint.context.Context;
import diamond.view.PreviewFrame;

/**
 * @author long_
 *
 */
public class PreviewAction implements ActionListener {
    Context context;

    public PreviewAction(Context context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PreviewFrame(context);
    }

}
