/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.option;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diamond.model.cyborg.graphics.SegDrawer;
import diamond.model.math.fuzzy.Util;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class PanelLine extends JPanel {
    private JTextField textField = new JTextField();

    public PanelLine() {
        super();
        buildTextField();
        setLayout(new FlowLayout());
        add(new JLabel(Labels.get("clipping_scale")));
        add(textField);
        add(new JLabel("%"));
    }

    private void buildTextField() {
        textField.setText(String.valueOf(SegDrawer.CUT));
        textField.setColumns(4);
        textField.addActionListener(new Action());
        textField.setHorizontalAlignment(JTextField.RIGHT);
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int input = Integer.parseInt(textField.getText());
            int set = Util.hairCut(input, 0, 100);
            SegDrawer.CUT = set;
            textField.setText(String.valueOf(set));
        }
    }
}
