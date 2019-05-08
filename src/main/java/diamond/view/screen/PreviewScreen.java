/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.diagram.Diagram;
import diamond.view.screen.draw.style.PageStyle;
import diamond.view.ui.label.PageNorthernLabel;
import diamond.view.ui.label.PageSouthernLabel;
import diamond.view.ui.panel.DiagramPanel;

/**
 * @author long_
 *
 */
public class PreviewScreen extends JPanel {
    private int page = 0;
    private PaintContext paintContext;

    public void nextPage(int pages) {
        page = Math.max(0, page + pages);
    }

    public PreviewScreen(PaintContext paintContext) {
        this.paintContext = paintContext;
        setBackground(Color.white);
        setBorder(new LineBorder(Color.white));
        setLayout(new BorderLayout());
        add(build(), BorderLayout.CENTER);
        add(new PageNorthernLabel(), BorderLayout.NORTH);
        add(new PageSouthernLabel(page + 1), BorderLayout.SOUTH);
    }

    public JPanel build() {
        if (page == 0) {
            //buildFirstPage();
            return buildNormalPage();
        } else {
            return buildNormalPage();
        }
    }

    private JPanel buildNormalPage() {
        int n = PageStyle.DIAGRAM_ROW * PageStyle.DIAGRAM_COL;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(
                PageStyle.DIAGRAM_ROW,
                PageStyle.DIAGRAM_COL));
        panel.setBackground(Color.white);
        LinkedList<Diagram> diagrams = paintContext.palette.getDiagrams();
        for (int i = page * n; i < (1 + page) * n; ++i) {
            if (i < diagrams.size()) {
                panel.add(buildDiagram(diagrams.get(i)));
            }
        }
        return panel;
    }

    private JComponent buildDiagram(Diagram diagram) {
        int stepNo = paintContext.palette.getDiagrams().indexOf(diagram);
        return new DiagramPanel(diagram, stepNo);
    }
    //    private void buildFirstPage() {
    //        add(buildDescription());
    //        add(buildGoal());
    //    }

    //    private JComponent buildGoal() {
    //        return buildDiagram(paintContext.palette.getDiagrams().getLast());
    //    }
    //
    //    private JComponent buildDescription() {
    //        return new JPanel();
    //    }

}
