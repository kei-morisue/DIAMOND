/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diamond.controller.Context;
import diamond.model.cyborg.style.StyleSegment;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class PanelLine extends JPanel {
    public PanelLine(Context context) {
        super();
        add(new JLabel(Labels.get("clipping_scale")));
        StyleSegment styleSegment = context.getDiagram().getStyleSegment();
        double clip = styleSegment.getClip();
        JTextField clipScale = new JTextField(String.valueOf(100 * clip));
        clipScale.setColumns(3);
        clipScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double scale = clip;
                try {
                    scale = Double.parseDouble(clipScale.getText());
                } catch (NumberFormatException e1) {
                    clipScale.setText(String.valueOf(scale));
                    return;
                }
                if (scale > 100.0 || scale < .0) {
                    clipScale.setText(String.valueOf(clip));
                    return;
                }
                styleSegment.setClip(scale * 0.01);
                //                context.repaint();
            }
        });
        clipScale.setHorizontalAlignment(JTextField.RIGHT);
        add(clipScale);
        add(new JLabel("%"));
    }
}
