/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.diagram.OrdinalPage;
import diamond.view.ui.label.PageNorthernLabel;
import diamond.view.ui.label.PageSouthernLabel;

/**
 * @author long_
 *
 */
public class PreviewScreen extends JPanel {
    private int pageNo = 0;
    private PaintContext paintContext;

    public void nextPage(int pages) {
        pageNo = Math.max(0, pageNo + pages);
    }

    public PreviewScreen(PaintContext paintContext) {
        this.paintContext = paintContext;
        setBackground(Color.white);
        setBorder(new LineBorder(Color.black));
        setLayout(new BorderLayout());
        add(buildPage(), BorderLayout.CENTER);
        add(new PageNorthernLabel(), BorderLayout.NORTH);
        add(new PageSouthernLabel(pageNo + 1), BorderLayout.SOUTH);
    }

    public JPanel buildPage() {
        LinkedList<Diagram> diagrams = paintContext.palette.getDiagrams();

        if (pageNo == 0) {
            return new OrdinalPage(pageNo, diagrams);
        } else {
            return new OrdinalPage(pageNo, diagrams);
        }
    }
}
