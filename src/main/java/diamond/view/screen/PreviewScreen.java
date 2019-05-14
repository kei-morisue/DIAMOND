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
import diamond.model.geom.element.diagram.TopPage;
import diamond.view.ui.label.PageNorthernLabel;
import diamond.view.ui.label.PageSouthernLabel;

/**
 * @author long_
 *
 */
public class PreviewScreen extends JPanel {
    private int pageNo = 0;
    private PaintContext paintContext;
    private JPanel page;

    public PreviewScreen(PaintContext paintContext) {
        this.paintContext = paintContext;
        build();
    }

    private void build() {
        setBackground(Color.white);
        setBorder(new LineBorder(Color.black));
        setLayout(new BorderLayout());
        page = buildPage();
        add(page, BorderLayout.CENTER);
        add(new PageNorthernLabel(), BorderLayout.NORTH);
        add(new PageSouthernLabel(pageNo + 1), BorderLayout.SOUTH);
    }

    public JPanel buildPage() {
        LinkedList<Diagram> diagrams = paintContext.palette.getDiagrams();
        if (pageNo == 0) {
            return new TopPage(diagrams);

        } else {
            return new OrdinalPage(pageNo, diagrams);
        }
    }

    public void nextPage(int pages) {
        pageNo = Math.max(0, pageNo + pages);
        removeAll();
        build();
        validate();
        repaint();
    }

}
