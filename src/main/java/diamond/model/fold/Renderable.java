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
public interface Renderable {

	abstract public XY centroid();

	abstract public boolean isPicked();

	abstract public void setPicked(
			boolean picked);

}
