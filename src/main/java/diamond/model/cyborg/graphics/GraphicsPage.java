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
public interface GraphicsPage {
    public void drawP(Graphics2D g2d, Diagram diagram);

    public void setG2dP(Graphics2D g2d, Diagram diagram);

}
