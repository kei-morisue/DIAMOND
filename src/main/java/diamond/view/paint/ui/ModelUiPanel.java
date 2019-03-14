/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class ModelUiPanel extends JPanel {
    ButtonGroup modelEditButtons = new ButtonGroup();

    private JPanel modelEditPanel = new JPanel();

    public ModelUiPanel(PaintContext context) {
        setLayout(new GridLayout(5, 1));
        addModelEditPanel(context);
    }

    private void setBorder(JPanel panel, String title) {
        TitledBorder border = new TitledBorder(
                new LineBorder(Color.GRAY, 2),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP);
        panel.setBorder(border);
    }

    private void addModelEditPanel(PaintContext context) {
        modelEditPanel.setLayout(new GridLayout(1, 2));
        modelEditPanel.add(new JRadioButton("Modify Vertex Position"));//TODO implement Actions!
        modelEditPanel.add(new JRadioButton("Modify Face Order"));//TODO implement Actions!
        add(modelEditPanel);
        setBorder(
                modelEditPanel,
                ResourceHolder.getLabelString(LABEL.MODEL_EDIT));
    }

}
