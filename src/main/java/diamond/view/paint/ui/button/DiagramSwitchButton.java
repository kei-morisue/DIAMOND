/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.paint.ModelContext;
import diamond.controller.paint.Palette;
import diamond.view.resource.ImageIconLoader;

/**
 * @author long_
 *
 */
public class DiagramSwitchButton extends JButton {
    public static final int PREV = -1;
    public static final int NEXT = 1;

    private int direction;
    private ModelContext context;

    public DiagramSwitchButton(Integer direction, ModelContext context) {
        setBackground(Color.white);
        this.direction = direction;
        this.context = context;
        ImageIconLoader imgLoader = new ImageIconLoader();
        switch (direction) {
        case PREV:
            setIcon(imgLoader.loadAsIcon("icon/left.gif"));
            break;
        default:
            setIcon(imgLoader.loadAsIcon("icon/right.gif"));
            break;
        }
        addActionListener(new Action());
    }

    private class Action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Palette palette = context.palette;
            if (palette.getStepNo() == 0 && direction == PREV) {
                return;
            }
            if (palette.size() == palette.getStepNo()
                    && direction == NEXT) {
                return;
            }
            palette.getCP().getDiagram().setTransform(context.transform);
            palette.setStepNo(palette.getStepNo() + direction);
            context.transform = palette.getCP().getDiagram().getTransform();

        }
    }
}
