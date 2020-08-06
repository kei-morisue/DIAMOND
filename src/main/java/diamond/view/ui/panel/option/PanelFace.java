/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.option;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.controller.action.ChooseColor;
import diamond.model.cyborg.style.StyleFace;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class PanelFace extends JPanel {
    JButton faceFront = new JButton(Labels.get("face_front_color"));
    JButton faceBack = new JButton(Labels.get("face_back_color"));
    StyleFace styleFace;

    public PanelFace(Context context) {
        StyleFace styleFace = context.getDiagram().getStyleFace();
        faceFront.setBackground(styleFace.getFront());
        faceBack.setBackground(styleFace.getBack());
        faceFront.addActionListener(
                new ChooseColor(
                        faceFront,
                        styleFace.new FrontSetter()));
        faceBack.addActionListener(
                new ChooseColor(
                        faceBack,
                        styleFace.new BackSetter()));
        setLayout(new GridLayout(2, 1));
        add(faceFront);
        add(faceBack);
    }
}