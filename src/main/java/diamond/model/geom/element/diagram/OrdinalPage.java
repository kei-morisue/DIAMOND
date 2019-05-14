/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

import diamond.view.screen.draw.style.PageStyle;

/**
 * @author long_
 *
 */
public class OrdinalPage extends JPanel {
    private final static int n = PageStyle.DIAGRAM_ROW * PageStyle.DIAGRAM_COL;

    public OrdinalPage(int pageNo, LinkedList<Diagram> diagrams) {
        setLayout(new GridLayout(
                PageStyle.DIAGRAM_ROW,
                PageStyle.DIAGRAM_COL));
        setBackground(Color.white);
        for (int i = pageNo * n - 1; i < (pageNo + 1) * n; ++i) {
            if (i < diagrams.size()) {
                Diagram diagram = diagrams.get(i);
                add(new Step(diagram, i));
            }
        }
    }

}
