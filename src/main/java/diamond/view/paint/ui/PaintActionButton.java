/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.action.Axiom1Action;
import diamond.controller.paint.action.Axiom2Action;
import diamond.controller.paint.action.Axiom3Action;
import diamond.controller.paint.action.Axiom4Action;
import diamond.controller.paint.action.DeleteLineAction;
import diamond.controller.paint.action.FlipLineTypeAction;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.view.resource.ImageIconLoader;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class PaintActionButton extends JRadioButton implements ActionListener {
    private PaintContext paintContext;
    private PaintActionInterface paintAction;

    public PaintActionButton(LABEL label, PaintContext context) {
        this.paintContext = context;
        switch (label) {
        case AXIOM1:
            setIcons("axiom1");
            this.paintAction = new Axiom1Action();
            break;
        case AXIOM2:
            setIcons("axiom2");
            this.paintAction = new Axiom2Action();
            break;
        case AXIOM3:
            setIcons("axiom3");
            this.paintAction = new Axiom3Action();
            break;
        case AXIOM4:
            setIcons("axiom4");
            this.paintAction = new Axiom4Action();
            break;
        case FLIP_LINE_TYPE:
            this.setText(ResourceHolder.getLabelString(label));
            this.paintAction = new FlipLineTypeAction();
            break;
        case DELETE_LINE:
            this.setText(ResourceHolder.getLabelString(label));
            this.paintAction = new DeleteLineAction();
            break;
        default:
            break;
        }
        addActionListener(this);
    }

    private void setIcons(String iconBaseName) {
        ImageIconLoader imgLoader = new ImageIconLoader();
        setIcon(
                imgLoader
                        .loadAsIcon("icon/" + iconBaseName + ".gif"));
        setSelectedIcon(
                imgLoader
                        .loadAsIcon("icon/" + iconBaseName + "_p.gif"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            paintContext.paintAction = paintAction;
        }
    }
}
