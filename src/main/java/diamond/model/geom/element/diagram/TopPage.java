/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;

import diamond.view.screen.draw.style.PageStyle;

/**
 * @author long_
 *
 */
public class TopPage extends JPanel {
    private final static int n = PageStyle.DIAGRAM_ROW
            * PageStyle.DIAGRAM_COL - 1;

    public TopPage(Vector<Diagram> diagrams) {
        setLayout(new GridLayout(
                PageStyle.DIAGRAM_ROW,
                PageStyle.DIAGRAM_COL));
        setBackground(PageStyle.bg);
        add(new Goal(diagrams));
        for (int i = 0; i < n; ++i) {
            if (i < diagrams.size()) {
                Diagram diagram = diagrams.get(i);
                add(new Step(diagram, i));
            }
        }
    }

}
