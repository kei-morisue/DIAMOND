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
import diamond.model.fold.Fold;
import diamond.model.fold.Vertex;
import diamond.view.draw.color.ColorProviderBase;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public class Drawer extends DrawerBase {

	private Fold fold;
	private double EPS;

	public Drawer(Fold fold, ColorProviderBase colorProvider, ShapeProviderBase shapeProvider) {
		super(colorProvider, shapeProvider);
		this.fold = fold;
		double minLength = getMInLength(shapeProvider);
		this.EPS = minLength / 3;
	}

	private double getMInLength(ShapeProviderBase shapeProvider) {
		ArrayList<XY> p = new ArrayList<>();
		ArrayList<XY> q = new ArrayList<>();
		getEdges().forEach(e -> {
			p.add(shapeProvider.getXY(e.getV0()));
			q.add(shapeProvider.getXY(e.getV1()));
		});
		double minLength = Geo.minLength(p, q);
		return minLength;
	}

	public void setColorProvider(ColorProviderBase colorProvider) {
		this.colorProvider = colorProvider;
	}

	public void setShapeProvider(ShapeProviderBase shapeProvider) {
		this.shapeProvider = shapeProvider;
	}

	@Override
	protected ArrayList<Vertex> getVertices() {
		return fold.getVertices();
	}

	@Override
	protected ArrayList<Edge> getEdges() {
		return fold.getEdges();
	}

	@Override
	protected ArrayList<Face> getFaces() {
		return fold.getFaces();
	}

	public Vertex pickVertex(XY v0, double scale) {
		ArrayList<Vertex> vertices = getVertices();
		vertices.forEach(vertex -> vertex.picked = false);
		for (Vertex vertex : vertices) {
			XY xy = shapeProvider.getXY(vertex);
			if (Geo.close(xy, v0, EPS / scale)) {
				vertex.picked = true;
				return vertex;
			}
		}
		return null;
	}

	public void clearPicked() {
		getVertices().forEach(v -> v.picked = false);
		getEdges().forEach(e -> e.picked = false);
		getFaces().forEach(f -> f.picked = false);
	}

}
