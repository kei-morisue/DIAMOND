/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class ScreenFitter {

    public static AffineTransform fit(
            AbstractScreen screen,
            Rectangle2D.Double rect,
            Cp cp) {
        ScreenTransform transform = cp.getTransform();

        int w = screen.getWidth();
        int h = screen.getHeight();

        double wr = rect.getWidth();
        double hr = rect.getHeight();
        double rcx = rect.x + wr / 2;
        double rcy = rect.y + hr / 2;

        transform.shift(getShit(w, rcx), getShit(h, rcy));
        transform.zoom(getScale(w, h, wr, hr));
        return transform;

    }

    private static double getScale(
            double w,
            double h,
            double wr,
            double hr) {
        return Math.min(
                w / wr,
                h / hr);
    }

    private static double getShit(
            double w,
            double rcx) {
        return w / 2 - rcx;
    }
}
