/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.util.CpClipper;
import diamond.view.ui.screen.draw.FoldedScreenDrawer;
import diamond.view.ui.screen.draw.StringDrawer;

/**
 * @author Kei Morisue
 *
 */
public class TutorialStep extends AbstractStep {
    private int stepNo;

    public TutorialStep(Cp cp, int stepNo) {
        super(cp);
        this.stepNo = stepNo + 1;
        setBackground(new Color(0, 0, 0, 0));
    }

    @Override
    protected void draw(Graphics2D g2d) {
        g2d.setTransform(cp.getTransform());
        FoldedScreenDrawer.draw(g2d, cp);
        FoldedScreenDrawer.draw(g2d, CpClipper.clip(cp));
        StringDrawer.drawTutorialStepNo(g2d, stepNo, x, y);
    }

}
