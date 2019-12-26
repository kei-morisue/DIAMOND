/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.StepOrdinal;
import diamond.view.ui.screen.style.PageStyle;

/**
 * @author Kei Morisue
 *
 */
public class PageOrdinal extends JPanel {
    public PageOrdinal(int pageNo, Vector<Cp> cps) {
        int N = PageStyle.DIAGRAM_ROW * PageStyle.DIAGRAM_COL;
        setLayout(new GridLayout(
                PageStyle.DIAGRAM_ROW,
                PageStyle.DIAGRAM_COL));
        setBackground(PageStyle.bg);
        for (int i = pageNo * N - 1; i < (pageNo + 1) * N - 1; ++i) {
            if (i < cps.size()) {
                Cp cp = cps.get(i);
                add(new StepOrdinal(cp, i));
            }
        }
    }
}
