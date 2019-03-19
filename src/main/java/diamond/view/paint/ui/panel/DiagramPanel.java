/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.panel;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import diamond.controller.paint.PaintContext;
import diamond.view.paint.ui.DiagramIcon;

/**
 * @author long_
 *
 */
public class DiagramPanel extends JScrollPane {
    LinkedList<DiagramIcon> diagramIcons = new LinkedList<>();
    PaintContext paintContext;

    public DiagramPanel(JPanel panel, PaintContext paintContext) {
        super(panel);
        this.paintContext = paintContext;
        ButtonGroup buttonGroup = new ButtonGroup();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < 20; ++i) {
            DiagramIcon button = new DiagramIcon();
            button.setPreferredSize(new Dimension(100, 100));
            diagramIcons.add(button);
            int step = diagramIcons.indexOf(button);
            button.setText(String.valueOf(step));
            panel.add(button);
            buttonGroup.add(button);
        }
    }
}
