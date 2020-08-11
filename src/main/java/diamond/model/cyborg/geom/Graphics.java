/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import java.awt.Graphics2D;

import diamond.model.cyborg.diagram.Diagram;

/**
 * @author Kei Morisue
 *
 */
public interface Graphics {
    public void draw(Graphics2D g2d, Diagram diagram);

    public void setG2d(Graphics2D g2d, Diagram diagram);

}
