/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.awt.Graphics2D;

import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public interface Metric<T extends F<T>> {
    public boolean isNear(double x, double y, double scale);

    public double distSquare(double x, double y);

    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            boolean isPointed);

}
