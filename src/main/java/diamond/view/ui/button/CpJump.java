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
public class CpJump extends JButton {
    public static final int BOTTOM = 0;
    public static final int TOP = 1;

    private int direction;
    private Context context;

    public CpJump(int direction, Context context) {
        setBackground(Color.white);
        this.direction = direction;
        this.context = context;
        switch (direction) {
        case BOTTOM:
            IconBuilder.set(this, "dleft.png");
            break;
        case TOP:
            IconBuilder.set(this, "dright.png");
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
            Palette palette = context.getPalette();
            switch (direction) {
            case BOTTOM:
                context.setCurrentStep(0);
                break;
            default:
                Vector<Cp> cps = palette.getCps();
                context.setCurrentStep(cps.size() - 1);
                break;
            }
            context.getPaintScreen().repaint();
            context.getFoldedScreen().repaint();

        }
    }
}
