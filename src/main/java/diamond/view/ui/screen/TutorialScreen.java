/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public class TutorialScreen extends AbstractPreviewScreen {

    public TutorialScreen(Context context) {
        super(context);
    }

    @Override
    public int maxPageNo() {
        return context.getPalette().getCps().size() - 1;
    }

    @Override
    protected JLabel buildNorthernLabel(int pageNo) {
        return new JLabel();
    }

    @Override
    protected JLabel buildSouthernLabel(int pageNo) {
        return new JLabel();
    }

    @Override
    protected JPanel buildPage() {
        Vector<Cp> cps = context.getPalette().getCps();
        if (pageNo == 0) {
            return new TutorialGoal(cps);

        } else {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0, 0, 0, 0));
            panel.setLayout(new GridLayout(1, 2));
            panel.add(new TutorialStep(cps.get(pageNo - 1), pageNo - 1));
            panel.add(new TutorialResult(
                    cps.get(pageNo),
                    pageNo,
                    cps.get(pageNo - 1)));
            return panel;
        }
    }

}