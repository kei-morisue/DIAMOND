/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.controller.paint.action.arrow.FoldUnfoldArrowPaintAction;
import diamond.controller.paint.action.arrow.MountainArrowPaintAction;
import diamond.controller.paint.action.arrow.ValleyArrowPaintAction;
import diamond.view.resource.IconSetter;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class ArrowPaintButton extends JRadioButton implements ActionListener {
    private PaintContext paintContext;
    private PaintActionInterface paintAction;

    public ArrowPaintButton(LABEL label, PaintContext context) {
        this.paintContext = context;
        switch (label) {
        case VALLEY_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ValleyArrowPaintAction() {
            };
            break;
        case MOUNTAIN_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new MountainArrowPaintAction() {
            };
            break;
        case FOLD_UNFOLD_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new FoldUnfoldArrowPaintAction() {
            };
            break;

        default:
            break;
        }
        addActionListener(this);
    }

    private void setIcons(String iconBaseName) {
        IconSetter.set(this, iconBaseName + ".gif");
        IconSetter.setSelected(this, iconBaseName + "_p.gif");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            paintContext.initialize();
            paintContext.paintAction = paintAction;
        }
    }
}
