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
public class ColorProviderCell implements ColorProviderBase {

	@Override
	public Color getColor(Vertex vertex) {
		return vertex.isPicked ? Color.GREEN : Color.WHITE;
	}

	@Override
	public Color getColor(Face face) {
		return face.isPicked ? Color.GREEN : Color.GRAY;
	}

	@Override
	public Color getColor(Edge edge) {
		return Color.BLACK;
	}

}
