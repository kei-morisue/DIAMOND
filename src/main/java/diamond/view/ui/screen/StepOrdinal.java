/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.border.MatteBorder;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.draw.FoldedScreenDrawer;
import diamond.view.ui.screen.draw.StringDrawer;
import diamond.view.ui.screen.style.PageStyle;

/**
 * @author Kei Morisue
 *
 */
public class StepOrdinal extends AbstractStep {
    private int stepNo;

    public StepOrdinal(Cp cp, int stepNo) {
        super(cp);
        this.stepNo = stepNo;
        setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
        setBackground(PageStyle.bg);
    }

    @Override
    protected void draw(Graphics2D g2d) {
        FoldedScreenDrawer.draw(g2d, cp);
        StringDrawer.drawDiagramStepNo(g2d, stepNo + 1, x, y);
    }

}
