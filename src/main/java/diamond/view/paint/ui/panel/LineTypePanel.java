/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.LineType;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class LineTypePanel extends JPanel {
    ButtonGroup lineTypeButtons = new ButtonGroup();

    public LineTypePanel(PaintContext context, ButtonGroup paintActionButtons) {
        addLineTypePanel(context, paintActionButtons);
    }

    private void addLineTypePanel(PaintContext context,
            ButtonGroup paintActionButtons) {
        setLayout(new GridLayout(2, 2));
        addLineTypeButton(LABEL.AUX_VALLEY, LineType.AUX_VALLEY, context);
        addLineTypeButton(LABEL.AUX_MOUNTAIN, LineType.AUX_MOUNTAIN, context);
        addLineTypeButton(LABEL.AUX, LineType.AUX, context);
        add(new InputLinePanel(context, paintActionButtons));
        UiPanelUtil.setBorder(
                this,
                ResourceHolder.getLabelString(LABEL.INPUT_LINE));
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
        add(button);
        if (label == LABEL.AUX_VALLEY) {
            button.setSelected(true);
        }
    }

}
