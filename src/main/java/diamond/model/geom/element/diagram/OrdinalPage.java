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
public class OrdinalPage extends JPanel {
    private final static int N = PageStyle.DIAGRAM_ROW * PageStyle.DIAGRAM_COL;

    public OrdinalPage(int pageNo, Vector<Diagram> diagrams) {
        setLayout(new GridLayout(
                PageStyle.DIAGRAM_ROW,
                PageStyle.DIAGRAM_COL));
        setBackground(PageStyle.bg);
        for (int i = pageNo * N - 1; i < (pageNo + 1) * N - 1; ++i) {
            if (i < diagrams.size()) {
                Diagram diagram = diagrams.get(i);
                add(new Step(diagram, i));
            }
        }
    }

}
