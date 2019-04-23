/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import diamond.controller.option.FaceBackColorStyleAction;
import diamond.controller.option.FaceFrontColorStyleAction;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.draw.style.ColorStyle;

/**
 * @author long_
 *
 */
public class FaceOption extends JPanel {
    public FaceOption() {
        String faceFrontLabel = ResourceHolder
                .getLabelString(LABEL.FACE_FRONT_STYLE);
        String faceBackLabel = ResourceHolder
                .getLabelString(LABEL.FACE_BACK_STYLE);
        JButton faceFront = new JButton(faceFrontLabel);
        JButton faceBack = new JButton(faceBackLabel);

        faceFront.setBackground(ColorStyle.ORI_FACE_FRONT);
        faceBack.setBackground(ColorStyle.ORI_FACE_BACK);

        faceFront.addActionListener(new FaceFrontColorStyleAction<JButton>());
        faceBack.addActionListener(new FaceBackColorStyleAction<JButton>());

        setLayout(new GridLayout(2, 1));
        add(faceFront);
        add(faceBack);
    }
}
