/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow.head;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import diamond.model.symbol.arrow.body.AbstractArrowBody;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractArrowHead implements Serializable {
	protected boolean isTail = false;
	static protected final Color COLOR_SELECTED = Color.green;

	public AbstractArrowHead() {
	}

	abstract public void draw(Graphics2D g2d, Point2D.Double tail, Point2D.Double head, AbstractArrowBody body,
			boolean isSelected);

	public void flip() {
		isTail = !isTail;
	}

	@Deprecated
	public boolean isTail() {
		return isTail;
	}

	@Deprecated
	public void setTail(boolean isTail) {
		this.isTail = isTail;
	}

}
