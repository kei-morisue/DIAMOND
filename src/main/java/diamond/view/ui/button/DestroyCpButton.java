/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.Palette;
import diamond.model.geom.element.diagram.Diagram;
import diamond.view.resource.IconSetter;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.WARNING;

/**
 * @author long_
 *
 */
public class DestroyCpButton extends JButton {
    public DestroyCpButton(Context context) {
        setBackground(Color.white);
        IconSetter.set(this, "destroy.png");
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Palette palette = context.getPalette();
                Vector<Diagram> diagrams = palette.getDiagrams();
                if (JOptionPane.showConfirmDialog(null,
                        ResourceHolder
                                .getWarningString(WARNING.DESTROY)) == 0
                        && diagrams.size() != 1) {
                    palette
                            .removeCp(palette.getDiagram());
                }

            }
        });
    }
}
