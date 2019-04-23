/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw.style;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import diamond.Initials;
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
        setSize(Initials.STYLE_FRAME_WIDTH, Initials.STYLE_FRAME_HEIGHT);
        JTabbedPane pane = new JTabbedPane();
        pane.addTab(
                ResourceHolder.getLabelString(LABEL.LINE_TAB),
                buildLineTab());
        pane.addTab(
                ResourceHolder.getLabelString(LABEL.FACE_TAB),
                buildFaceTab());

        add(pane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel buildLineTab() {
        JPanel tab = new JPanel();
        tab.add(new JLabel(
                ResourceHolder.getLabelString(LABEL.CLIPPING_SCALE)));
        JTextField clipScale = new JTextField(
                String.valueOf(LineStyle.CLIP_SCALE));
        tab.add(clipScale);
        return tab;
    }

    private JPanel buildFaceTab() {
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
        JPanel tab = new JPanel();

        tab.setLayout(new GridLayout(2, 1));
        tab.add(faceFront);
        tab.add(faceBack);
        return tab;
    }
}
