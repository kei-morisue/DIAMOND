/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.draw.FoldedScreenDrawer;

/**
 * @author Kei Morisue
 *
 */
public class TutorialResult extends AbstractStep {
    private Cp cp0;

    public TutorialResult(Cp cp, int stepNo, Cp cp0) {
        super(cp);
        this.cp0 = cp0;
        setBackground(new Color(0, 0, 0, 0));
        setBackground(new Color(255, 255, 255, 255));
    }

    @Override
    protected void draw(Graphics2D g2d) {
        //      Rectangle2D.Double clip = CpClipper.clip(cp);
        //      g2d.setTransform(ScreenFitter.fit(this, clip, cp));
        g2d.setTransform(cp0.getTransform());
        //              FoldedScreenDrawer.draw(g2d, clip);

        FoldedScreenDrawer.drawResult(g2d, cp);
    }
}
