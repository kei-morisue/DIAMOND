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
public class ColorProviderFlat implements ColorProviderBase {
	@Override
	public Color getColor(Vertex vertex) {
		return vertex.isPicked ? Color.GREEN : Color.BLACK;

	}

	@Override
	public Color getColor(Face face) {
		return face.isPicked ? Color.GREEN : Color.DARK_GRAY;

	}

	@Override
	public Color getColor(Edge edge) {
		Edge.Assign assign = edge.getA();
		switch (assign) {
		case M:
			return (Color.RED);
		case V:
			return (Color.BLUE);
		case F:
			return (Color.LIGHT_GRAY);
		default:
			return (Color.WHITE);
		}
	}

}
