/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.color;

import java.awt.Color;

import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public interface ColorProviderBase {

	abstract public Color getColor(Vertex vertex);

	abstract public Color getColor(Face face);

	abstract public Color getColor(Edge edge);
}
