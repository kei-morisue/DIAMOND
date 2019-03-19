/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.diagram;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author long_
 *
 */
public class DiagramPane extends JScrollPane {
    LinkedList<DiagramIcon> diagramIcons = new LinkedList<>();

    public DiagramPane(JPanel panel) {
        super(panel);
        ButtonGroup buttonGroup = new ButtonGroup();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        for (int i = 0; i < 200; ++i) {
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
