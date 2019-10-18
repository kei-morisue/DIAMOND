/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.paint.action.ArrowPaintAction;
import diamond.controller.paint.action.ArrowScalingAction;
import diamond.controller.paint.action.ArrowSelectAction;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.diagram.arrow.FlipArrow;
import diamond.model.geom.element.diagram.arrow.FoldUnfoldArrow;
import diamond.model.geom.element.diagram.arrow.MountainFoldArrow;
import diamond.model.geom.element.diagram.arrow.RotateArrow;
import diamond.model.geom.element.diagram.arrow.SinkArrow;
import diamond.model.geom.element.diagram.arrow.ValleyFoldArrow;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class ArrowPaintButton extends JRadioButton implements ActionListener {
    private PaintScreenContext paintContext;
    private PaintActionInterface paintAction;

    public ArrowPaintButton(LABEL label, PaintScreenContext context) {
        this.paintContext = context;
        switch (label) {
        case VALLEY_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowPaintAction(ValleyFoldArrow.class);
            break;
        case MOUNTAIN_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowPaintAction(MountainFoldArrow.class);
            break;
        case FOLD_UNFOLD_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowPaintAction(FoldUnfoldArrow.class);
            break;
        case SINK_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowPaintAction(SinkArrow.class);
            break;
        case FLIP_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowPaintAction(FlipArrow.class);
            break;
        case ROTATE_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowPaintAction(RotateArrow.class);
            break;
        case SELECT_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowSelectAction();
            break;
        case SCALE_ARROW:
            setText(ResourceHolder.getLabelString(label));
            this.paintAction = new ArrowScalingAction();
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
