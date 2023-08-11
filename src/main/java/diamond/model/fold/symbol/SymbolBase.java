/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold.symbol;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.List;

import diamond.model.fold.Face;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public abstract class SymbolBase implements Serializable {

	public abstract Face getLayer(
			List<Face> faces);

	public abstract void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale);

	public abstract void flip();
}
