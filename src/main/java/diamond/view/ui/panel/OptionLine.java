/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.screen.style.HalfEdgeStyle;

/**
 * @author Kei Morisue
 *
 */
public class OptionLine extends JPanel {

    public OptionLine(Context context) {
        super();
        add(new JLabel(
                Labels.get("clipping_scale")));
        JTextField clipScale = new JTextField(
                String.valueOf(100 * HalfEdgeStyle.CLIP_SCALE));
        clipScale.setColumns(3);
        clipScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double scale = HalfEdgeStyle.CLIP_SCALE;
                try {
                    scale = Double.parseDouble(clipScale.getText());
                } catch (NumberFormatException e1) {
                    clipScale.setText(String.valueOf(scale));
                    return;
                }
                if (scale > 100.0 || scale < .0) {
                    clipScale.setText(String.valueOf(HalfEdgeStyle.CLIP_SCALE));
                    return;
                }
                HalfEdgeStyle.CLIP_SCALE = scale * 0.01;
                context.repaint();
            }
        });
        clipScale.setHorizontalAlignment(JTextField.RIGHT);
        add(clipScale);
        add(new JLabel("%"));
    }
}
