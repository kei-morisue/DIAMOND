/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

/**
 * @author Kei Morisue
 *
 */
public class Cp extends Flat {
	private Face baseFace;

	public Cp(double scale) {
		super();
		CpBuilder.buildSquare(this, scale);
		this.baseFace = getFaces().get(0);
		buildFolded(baseFace);
	}

	public void clearFolded() {
		vertices.forEach(v -> {
			v.f = v.p;
		});
		faces.forEach(faces -> {
			faces.isFlip = false;
		});
	}

	private void buildFolded(Face baseFace) {
		clearFolded();
		// TODO
	}

	@Override
	protected int getMaxFraction() {
		return 300;
	}

}
