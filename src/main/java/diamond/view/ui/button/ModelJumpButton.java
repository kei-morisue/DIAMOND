/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.Palette;
import diamond.model.geom.element.diagram.Diagram;
import diamond.view.resource.IconSetter;
import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;

/**
 * @author long_
 *
 */
public class ModelJumpButton extends JButton {
    public static final int BOTTOM = -1;
    public static final int TOP = 1;

    private int direction;
    private ModelScreen modelScreen;
    private PaintScreen paintScreen;

    public ModelJumpButton(int direction, PaintScreen paintScreen,
            ModelScreen modelScreen) {
        setBackground(Color.white);
        this.direction = direction;
        this.modelScreen = modelScreen;
        this.paintScreen = paintScreen;
        switch (direction) {
        case BOTTOM:
            IconSetter.set(this, "dleft.png");
            break;
        default:
            IconSetter.set(this, "dright.png");
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Context context = modelScreen.getContext();
            Palette palette = context.getPalette();
            switch (direction) {
            case BOTTOM:
                palette.setStepNo(0, context);
                break;
            default:
                Vector<Diagram> diagrams = palette.getDiagrams();
                palette.setStepNo(diagrams.size() - 1, context);
                break;
            }
            modelScreen.repaint();
            paintScreen.repaint();
        }
    }
}
