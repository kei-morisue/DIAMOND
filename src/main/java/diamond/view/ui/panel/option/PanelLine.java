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

import diamond.controller.Context;
import diamond.model.cyborg.style.StyleSegment;
import diamond.model.math.Util;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class PanelLine extends JPanel {
    private JTextField textField = new JTextField();
    private StyleSegment styleSegment;

    public PanelLine(Context context) {
        super();
        styleSegment = context.getDiagram().getStyleSegment();
        buildTextField();
        setLayout(new FlowLayout());
        add(new JLabel(Labels.get("clipping_scale")));
        add(textField);
        add(new JLabel("%"));
    }

    private void buildTextField() {
        textField.setText(String.valueOf(100.0 * styleSegment.getClip()));
        textField.setColumns(4);
        textField.addActionListener(new Action());
        textField.setHorizontalAlignment(JTextField.RIGHT);
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double input = Double.parseDouble(textField.getText());
            double set = Util.window(input, .0, 100.0);
            styleSegment.setClip(set * 0.01);
            textField.setText(String.valueOf(set));
        }
    }
}
