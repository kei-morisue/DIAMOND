/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.border.MatteBorder;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.draw.FoldedScreenDrawer;

/**
 * @author Kei Morisue
 *
 */
public class StepGoal extends AbstractStep {

    public StepGoal(List<Cp> cps) {
        super(cps.get(cps.size() - 1));
        setBorder(new MatteBorder(2, 2, 2, 2, Color.black));

    }

    @Override
    protected void draw(Graphics2D g2d) {
        FoldedScreenDrawer.draw(g2d, cp);
    }
}
