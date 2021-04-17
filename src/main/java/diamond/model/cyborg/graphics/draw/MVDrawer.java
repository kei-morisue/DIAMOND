/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class MVDrawer {
    final private static Color POINTED = Color.GREEN;
    final private static Color MOUNTAIN = Color.RED;
    final private static Color VALLEY = Color.BLUE;

    public static <T extends F<T>, S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            Ver<T> p,
            Ver<T> q,
            boolean isMountain,
            boolean isPointed) {
        g2d.setColor(getColor(isMountain, isPointed));
        g2d.setStroke(getMVStroke(isMountain, scale));
        g2d.draw(ShapeBuilder.build(p, q));
    }

    private static Color getColor(
            boolean isMountain,
            boolean isPointed) {
        Color color = (isMountain) ? MOUNTAIN : VALLEY;
        return (isPointed) ? POINTED : color;
    }

    private static BasicStroke getMVStroke(
            boolean isMountain,
            float scale) {
        if (isMountain) {
            float m[] = { 10.0f / scale, 2.0f / scale, 2.0f / scale,
                    2.0f / scale };
            return new BasicStroke(
                    3.0f / scale,
                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10.0f, m, 0.0f);
        } else {
            float v[] = { 10.0f / scale, 3.0f / scale };
            return new BasicStroke(
                    3.0f / scale,
                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10.0f, v, 0.0f);
        }

    }
}
