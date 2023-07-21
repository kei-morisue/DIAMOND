/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import diamond.model.XY;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class ShapeProviderFlat extends ShapeProviderBase {

	@Override
	public XY getXY(Vertex v) {
		return v.v;
	}

}
