/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.paint.action.LandmarkPaintAction;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class LandmarkPaintButton extends JRadioButton
        implements ActionListener {
    private PaintScreenContext paintContext;
    private PaintActionInterface paintAction;

    public LandmarkPaintButton(LABEL label, PaintScreenContext context) {
        this.paintContext = context;
        switch (label) {
        case LANDMARK:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new LandmarkPaintAction();
            break;
        default:
            break;
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            paintContext.initialize();
            paintContext.setPaintAction(paintAction);
        }
    }
}
