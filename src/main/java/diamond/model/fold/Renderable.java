/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.Color;
import java.awt.Shape;

import diamond.view.draw.color.ColorProviderBase;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public interface Renderable {
	public Shape getShape(ShapeProviderBase shapeProvider, double scale);

	public Color getColor(ColorProviderBase colorProvider);
}
