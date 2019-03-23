/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.button;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.paint.Palette;
import diamond.model.geom.element.cp.Cp;

/**
 * @author long_
 *
 */
public class DiagramIcon extends JRadioButton implements ActionListener {
    private Palette palette;

    private int stepNo = 0;

    public DiagramIcon(Palette palette, Cp cp) {
        this.palette = palette;

        setHorizontalTextPosition(JRadioButton.LEFT);
        setPreferredSize(new Dimension(100, 100));
    }

    public void setStep(int step) {
        this.stepNo = step;
        setText(String.valueOf(step + 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isSelected()) {
            palette.setStepNo(stepNo);
        }
    }
}
