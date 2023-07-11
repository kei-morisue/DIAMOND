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
public class ColorProviderFolded implements ColorProviderBase {
	@Override
	public Color getColor(Vertex vertex) {
		return (vertex.picked || vertex.selected) ? Color.GREEN : Color.BLACK;

	}

	@Override
	public Color getColor(Face face) {
		return face.isFlip() ? Color.DARK_GRAY : Color.WHITE;

	}

	@Override
	public Color getColor(Edge edge) {
		Edge.Assign assign = edge.getA();
		switch (assign) {
		case M:
			return (Color.BLACK);
		case V:
			return (Color.BLACK);
		case F:
			return (Color.LIGHT_GRAY);
		default:
			return (Color.BLACK);
		}
	}

}
