/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import diamond.controller.Context;
import diamond.controller.Palette;
import diamond.model.cyborg.Cp;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class CpDestroy extends JButton {
    public CpDestroy(Context context) {
        setBackground(Color.white);
        IconBuilder.set(this, "destroy.png");
        setFocusable(false);
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Palette palette = context.getPalette();
                Vector<Cp> cps = palette.getCps();
                if (JOptionPane.showConfirmDialog(null,
                        Labels.get("destroy_cp")) == 0
                        && cps.size() != 1) {
                    context.removeCp(context.getCp());
                }

            }
        });
    }
}
