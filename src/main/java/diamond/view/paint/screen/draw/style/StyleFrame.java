/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen.draw.style;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import diamond.controller.option.FaceBackColorStyleAction;
import diamond.controller.option.FaceFrontColorStyleAction;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class StyleFrame extends JFrame {
    public StyleFrame() {
        setTitle(ResourceHolder.getLabelString(LABEL.STYLE));
        setSize(300, 300);
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
        getContentPane().setLayout(new GridLayout(2, 1));
        getContentPane().add(faceFront);
        getContentPane().add(faceBack);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
