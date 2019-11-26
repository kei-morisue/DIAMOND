/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import diamond.controller.Context;
import diamond.model.cyborg.EdgeType;

/**
 * @author Kei Morisue
 *
 */
public class LineTypeButton extends AbstractIconButton {
    public LineTypeButton(EdgeType type, Context context,
            ButtonGroup buttonGroup) {
        addActionListener(new LinetypeButtonAction(type, context));
        buttonGroup.add(this);
        switch (type) {
        case UNSETTLED_VALLEY:
            setIcons("valley");
            break;
        case UNSETTLED_MOUNTAIN:
            setIcons("mountain");
            break;
        case CREASE:
            setIcons("crease");
            break;
        case CUT:
            setIcons("cut");
            break;
        default:
            break;
        }
    }

    public class LinetypeButtonAction implements ActionListener {
        Context context;
        EdgeType type;

        public LinetypeButtonAction(EdgeType type, Context context) {
            this.context = context;
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JRadioButton) e.getSource()).isSelected()) {
                context.setInputType(this.type);
            }
        }
    }
}
