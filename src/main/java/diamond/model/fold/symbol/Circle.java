/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold.symbol;

import java.awt.Graphics2D;
import java.util.List;

import diamond.model.fold.Face;
import diamond.model.fold.Vertex;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class Circle extends SymbolBase {
	private Vertex vertex;

	public Circle(Vertex vertex) {
		super();
		this.vertex = vertex;
	}

	public Vertex getVertex() {
		return vertex;
	}

	@Override
	public void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale) {
		drawer.draw(g2d, this, scale);
	}

	@Override
	public Face getLayer(
			List<Face> faces) {
		return vertex.getTopFace(faces);
	}

}
