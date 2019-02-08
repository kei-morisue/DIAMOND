/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.LineType;
import diamond.view.paint.screen.PaintScreen;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class panelUI extends JPanel {
    ButtonGroup paintActionButtons = new ButtonGroup();
    ButtonGroup lineTypeButtons = new ButtonGroup();

    JPanel lineTypePanel = new JPanel();

    public panelUI(PaintScreen screen, PaintContext context) {
        setLayout(new GridLayout(7, 1));
        addLineTypePanel(context);
        addPaintActionButton(LABEL.FLIP_LINE_TYPE, context);
        addPaintActionButton(LABEL.AXIOM1, context);
        addPaintActionButton(LABEL.AXIOM2, context);
        addPaintActionButton(LABEL.AXIOM3, context);
        addPaintActionButton(LABEL.AXIOM4, context);
        addPaintActionButton(LABEL.DELETE_LINE, context);
    }

    /**
     * @param context
     */
    private void addLineTypePanel(PaintContext context) {
        addLineTypeButton(LABEL.MOUNTAIN, LineType.MOUNTAIN, context);
        addLineTypeButton(LABEL.VALLEY, LineType.VALLEY, context);
        addLineTypeButton(LABEL.AUX, LineType.AUX, context);
        add(lineTypePanel);
    }

    private class LinetypeButtonAction implements ActionListener {
        PaintContext context;
        LineType type;

        public LinetypeButtonAction(LineType type, PaintContext context) {
            this.context = context;
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JRadioButton) e.getSource()).isSelected()) {
                context.inputLineType = this.type;
            }
        }
    }

    private void addLineTypeButton(LABEL label, LineType type,
            PaintContext context) {
        JRadioButton button = new JRadioButton(
                ResourceHolder.getLabelString(label));
        button.addActionListener(new LinetypeButtonAction(type, context));
        lineTypeButtons.add(button);
        lineTypePanel.add(button);
        if (label == LABEL.MOUNTAIN) {
            button.setSelected(true);
        }
    }

    private void addPaintActionButton(LABEL label, PaintContext context) {
        JRadioButton button = new PaintActionButton(label, context);
        add(button);
        paintActionButtons.add(button);
        if (label == LABEL.AXIOM1) {
            button.setSelected(true);
        }
    }
}
