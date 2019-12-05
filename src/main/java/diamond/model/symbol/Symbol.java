/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol;

import java.awt.Graphics2D;

import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public abstract class Symbol<T> {
    abstract public void draw(Graphics2D g2d);

    abstract public void set(T cyborg);

    abstract public void flip(Cp cp);
}
