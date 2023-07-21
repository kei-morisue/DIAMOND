/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.util.ArrayList;

import diamond.model.Geo;
import diamond.model.XY;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Flat;
import diamond.model.fold.Vertex;
import diamond.view.draw.color.ColorProviderBase;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public class Drawer extends DrawerBase {

	private Flat flat;
	private static final double EPS = 20.0;

	public Drawer(Flat fold, ColorProviderBase colorProvider, ShapeProviderBase shapeProvider) {
		super(colorProvider, shapeProvider);
		this.flat = fold;
	}

	public void setColorProvider(ColorProviderBase colorProvider) {
		this.colorProvider = colorProvider;
	}

	public void setShapeProvider(ShapeProviderBase shapeProvider) {
		this.shapeProvider = shapeProvider;
	}

	@Override
	protected ArrayList<Vertex> getVertices() {
		return flat.getVertices();
	}

	@Override
	protected ArrayList<Edge> getEdges() {
		return flat.getEdges();
	}

	@Override
	protected ArrayList<Face> getFaces() {
		return flat.getFaces();
	}

	public Vertex pickVertex(XY v0, double scale) {
		ArrayList<Vertex> vertices = getVertices();
		vertices.forEach(vertex -> vertex.isPicked = false);
		for (Vertex vertex : vertices) {
			XY xy = shapeProvider.getXY(vertex);
			if (Geo.isClose(xy, v0, EPS / scale)) {
				vertex.isPicked = true;
				return vertex;
			}
		}
		return null;
	}

	public void clearPicked() {
		getVertices().forEach(v -> v.isPicked = false);
		getEdges().forEach(e -> e.isPicked = false);
		getFaces().forEach(f -> f.isPicked = false);
	}

}
