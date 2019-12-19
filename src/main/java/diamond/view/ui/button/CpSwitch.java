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

import diamond.controller.Context;
import diamond.controller.Palette;
import diamond.model.cyborg.Cp;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class CpSwitch extends JButton {
    public static final int PREV = -1;
    public static final int NEXT = 1;

    private int direction;
    private Context context;

    public CpSwitch(int direction, Context context) {
        setBackground(Color.white);
        this.direction = direction;
        this.context = context;
        switch (direction) {
        case PREV:
            IconBuilder.set(this, "left.png");
            break;
        case NEXT:
            IconBuilder.set(this, "right.png");
            break;
        default:
            setText(Labels.get("no_image"));
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentStep = context.getCurrentStep();
            if (currentStep == 0 && direction == PREV) {
                return;
            }
            Palette palette = context.getPalette();
            Vector<Cp> cps = palette.getCps();
            if (cps.size() - 1 == currentStep && direction == NEXT) {
                return;
            }
            context.setCurrentStep(currentStep + direction);
            context.getPaintScreen().repaint();
            context.getFoldedScreen().repaint();

        }
    }
}
