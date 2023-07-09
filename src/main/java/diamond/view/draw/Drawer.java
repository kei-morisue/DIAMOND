/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.util.ArrayList;

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
	private ColorProviderBase colorProvider;
	private ShapeProviderBase shapeProvider;

	public Drawer(Fold fold, ColorProviderBase colorProvider, ShapeProviderBase shapeProvider) {
		super();
		this.fold = fold;
		this.colorProvider = colorProvider;
		this.shapeProvider = shapeProvider;
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

	@Override
	protected ColorProviderBase getColorProvider() {
		return colorProvider;
	}

	@Override
	protected ShapeProviderBase getShapeProvider() {
		return shapeProvider;
	}
}
