/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * @author Kei Morisue
 *
 */
public interface Cyborg extends Serializable {
	public Rectangle2D.Double clip(AffineTransform transform);
}
