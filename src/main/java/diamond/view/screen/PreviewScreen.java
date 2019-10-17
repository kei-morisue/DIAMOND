/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import diamond.controller.paint.context.PaintContext;
import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.diagram.OrdinalPage;
import diamond.model.geom.element.diagram.TopPage;
import diamond.view.screen.draw.style.PageStyle;
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
        Vector<Diagram> diagrams = paintContext.getPalette().getDiagrams();
        if (pageNo == 0) {
            return new TopPage(diagrams);

        } else {
            return new OrdinalPage(pageNo, diagrams);
        }
    }

    public void nextPage(int pages) {
        pageNo = Math.max(0, pageNo + pages);
        pageNo = Math.min(pageNo, maxPageNo());
        removeAll();
        build();
        validate();
        repaint();
    }

    public void setToTop() {
        pageNo = 0;
        removeAll();
        build();
        validate();
        repaint();
    }

    public int maxPageNo() {
        int steps = paintContext.getPalette().getDiagrams().size();
        int n = PageStyle.DIAGRAM_ROW * PageStyle.DIAGRAM_COL;
        if (steps < n) {
            return 0;
        }
        int rest = steps - n + 1;
        return (rest / n) + ((rest % n == 0) ? 0 : 1);
    }
}
