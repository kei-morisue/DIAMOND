/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.ModelScreenContext;
import diamond.controller.paint.context.Palette;
import diamond.view.resource.IconSetter;
import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;

/**
 * @author long_
 *
 */
public class ModelSwitchButton extends JButton {
    public static final int PREV = -1;
    public static final int NEXT = 1;

    private int direction;
    private ModelScreen modelScreen;
    private PaintScreen paintScreen;

    public ModelSwitchButton(int direction, PaintScreen paintScreen,
            ModelScreen modelScreen) {
        setBackground(Color.white);
        this.direction = direction;
        this.modelScreen = modelScreen;
        this.paintScreen = paintScreen;
        switch (direction) {
        case PREV:
            IconSetter.set(this, "left.png");
            break;
        default:
            IconSetter.set(this, "right.png");
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Context context = modelScreen.getContext();
            Palette palette = context.getPalette();
            if (palette.getStepNo() == 0 && direction == PREV) {
                return;
            }
            if (palette.size() == palette.getStepNo()
                    && direction == NEXT) {
                return;
            }
            ModelScreenContext modelScreenContext = context
                    .getModelScreenContext();
            palette.getDiagram()
                    .setTransform(modelScreenContext.getTransform());
            palette.setStepNo(palette.getStepNo() + direction);
            modelScreenContext
                    .setTransform(palette.getDiagram().getTransform());
            modelScreen.repaint();
            paintScreen.repaint();
        }
    }
}
