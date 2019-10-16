/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import diamond.controller.paint.context.PaintContext;
import diamond.view.resource.IconSetter;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.WARNING;

/**
 * @author long_
 *
 */
public class DiagramDestroyButton extends JButton {
    PaintContext paintContext;

    public DiagramDestroyButton(PaintContext paintContext) {
        this.paintContext = paintContext;
        setBackground(Color.white);
        IconSetter.set(this, "destroy.gif");
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        ResourceHolder
                                .getWarningString(WARNING.DESTROY)) == 0
                        && paintContext.palette.size() != 1) {
                    paintContext.palette
                            .remove(paintContext.palette.getDiagram());
                }

            }
        });
    }
}
