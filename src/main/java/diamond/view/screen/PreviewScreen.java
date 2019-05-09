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
    private JPanel mainScreen = new JPanel();

    public void nextPage(int pages) {
        page = Math.max(0, page + pages);
    }

    public PreviewScreen(PaintContext paintContext) {
        this.paintContext = paintContext;
        setBackground(Color.white);
        setBorder(new LineBorder(Color.black));
        setLayout(new BorderLayout());
        build();
        add(mainScreen, BorderLayout.CENTER);
        add(new PageNorthernLabel(), BorderLayout.NORTH);
        add(new PageSouthernLabel(page + 1), BorderLayout.SOUTH);
    }

    public void build() {
        if (page == 0) {
            //buildFirstPage();
            buildNormalPage();
        } else {
            buildNormalPage();
        }
    }

    private void buildNormalPage() {
        int n = PageStyle.DIAGRAM_ROW * PageStyle.DIAGRAM_COL;
        mainScreen.setLayout(new GridLayout(
                PageStyle.DIAGRAM_ROW,
                PageStyle.DIAGRAM_COL));
        mainScreen.setBackground(Color.white);
        LinkedList<Diagram> diagrams = paintContext.palette.getDiagrams();
        for (int i = page * n; i < (1 + page) * n; ++i) {
            if (i < diagrams.size()) {
                mainScreen.add(buildDiagram(diagrams.get(i)));
            }
        }
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
