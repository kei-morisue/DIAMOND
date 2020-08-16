/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import java.awt.geom.Rectangle2D;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.graphics.Graphics;

/**
 * @author Kei Morisue
 *
 */
public interface Cyborg extends Graphics {
    public double dist(Vertex v);

    public Rectangle2D.Double clip();
}
