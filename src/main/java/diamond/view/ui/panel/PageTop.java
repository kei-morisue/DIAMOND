/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.StepGoal;
import diamond.view.ui.screen.StepOrdinal;
import diamond.view.ui.screen.style.PageStyle;

/**
 * @author Kei Morisue
 *
 */
public class PageTop extends JPanel {
    private final static int N = PageStyle.DIAGRAM_ROW
            * PageStyle.DIAGRAM_COL - 1;

    public PageTop(Vector<Cp> cps) {
        setLayout(new GridLayout(
                PageStyle.DIAGRAM_ROW,
                PageStyle.DIAGRAM_COL));
        setBackground(PageStyle.bg);
        add(new StepGoal(cps));
        for (int i = 0; i < N; ++i) {
            if (i < cps.size() - 1) {
                Cp cp = cps.get(i);
                add(new StepOrdinal(cp, i));
            }
        }
    }

}
