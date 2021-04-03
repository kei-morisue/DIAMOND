/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;

import diamond.view.resource.string.Labels;
import diamond.view.ui.panel.option.Setter;

/**
 * @author Kei Morisue
 *
 */
public class ChooseColor implements ActionListener {
    private Component parent;
    private Setter<Color> colorSetter;

    public ChooseColor(Component parent, Setter<Color> colorSetter) {
        this.parent = parent;
        this.colorSetter = colorSetter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JColorChooser chooser = new JColorChooser(parent.getBackground());
        JDialog dialog = JColorChooser.createDialog(
                parent,
                Labels.get("option_page_bg_color"),
                true,
                chooser,
                new OkListner(chooser),
                new CancelListner());
        dialog.setVisible(true);
    }

    private class OkListner implements ActionListener {
        JColorChooser chooser;

        public OkListner(JColorChooser chooser) {
            this.chooser = chooser;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color chosen = chooser.getColor();
            if (chosen == null) {
                return;
            }
            colorSetter.set(chosen);
            parent.setBackground(chosen);
        }
    }

    private class CancelListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

}
