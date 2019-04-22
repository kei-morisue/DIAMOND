/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.controller.paint.PaintContext;
import diamond.view.PreviewFrame;

/**
 * @author long_
 *
 */
public class PreviewAction implements ActionListener {
    PaintContext paintContext;

    public PreviewAction(PaintContext paintContext) {
        this.paintContext = paintContext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PreviewFrame(paintContext);
    }

}
