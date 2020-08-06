/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d1.SegmentType;

/**
 * @author Kei Morisue
 *
 */
public class Color extends AbstractIconButton implements ActionListener {
    private Context context;
    private SegmentType type;

    public Color(
            SegmentType type,
            Context context,
            ButtonGroup buttonGroup) {
        this.context = context;
        this.type = type;
        addActionListener(this);
        buttonGroup.add(this);
        switch (type) {
        case CREASE_VALLEY:
            setIcons("valley");
            break;
        case CREASE_MOUNTAIN:
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JRadioButton) e.getSource()).isSelected()) {
            context.setType(this.type);
        }
    }
}