/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.draw.style.LineStyle;

/**
 * @author long_
 *
 */
public class LineOption extends JPanel {
    public LineOption() {
        super();
        add(new JLabel(
                ResourceHolder.getLabelString(LABEL.CLIPPING_SCALE)));
        JTextField clipScale = new JTextField(
                String.valueOf(LineStyle.CLIP_SCALE));
        clipScale.setColumns(3);
        clipScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int scale = LineStyle.CLIP_SCALE;
                try {
                    scale = Integer.parseInt(clipScale.getText());
                } catch (NumberFormatException e1) {
                    clipScale.setText(String.valueOf(scale));
                    return;
                }
                if (scale > 100 || scale < 0) {
                    clipScale.setText(String.valueOf(LineStyle.CLIP_SCALE));
                    return;
                }
                LineStyle.CLIP_SCALE = scale;
            }
        });
        clipScale.setHorizontalAlignment(JTextField.RIGHT);
        add(clipScale);
        add(new JLabel("%"));
    }
}
