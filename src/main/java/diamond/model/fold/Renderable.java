/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public abstract class Renderable {

	public boolean isPicked = false;
	public boolean isPointed = false;

	abstract public XY centroid();

}
