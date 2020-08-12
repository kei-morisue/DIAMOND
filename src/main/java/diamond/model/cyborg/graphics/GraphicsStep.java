/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics;

import java.awt.Graphics2D;

import diamond.model.cyborg.diagram.Diagram;

/**
 * @author Kei Morisue
 *
 */
public interface GraphicsStep extends GraphicsFolded {
    public void drawS(Graphics2D g2d, Diagram diagram);

    public void setG2dS(Graphics2D g2d, Diagram diagram);

}
