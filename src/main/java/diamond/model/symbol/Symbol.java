/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public abstract class Symbol<T> {
    abstract public void drawCp(Graphics2D g2d);

    abstract public void drawFolded(Graphics2D g2d);

    abstract public void set(T cyborg);

    abstract public void flip(Cp cp);

    abstract public void setOffset(Point2D.Double p);

    abstract public void setScale(double scale);

    abstract public void setSelected(boolean isSelected);

    abstract public Rectangle2D.Double clip();

}
