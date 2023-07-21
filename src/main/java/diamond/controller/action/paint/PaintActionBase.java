/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.action.Palette;

/**
 * @author Kei Morisue
 *
 */
public interface PaintActionBase {
	public abstract void doAction(Palette pallete);

	public abstract void undo(Palette pallete);

	public abstract void destroy(Palette pallete);

	public abstract void recover(Palette pallete);

	public abstract PaintActionBase onLeftClick(Palette pallete);

	public abstract PaintActionBase onRightClick(Palette pallete);

	public abstract void onMove(Palette pallete);

	public abstract void onPress(Palette pallete);

	public abstract void onDrag(Palette pallete);

	public abstract void onRelease(Palette pallete);

	public abstract void onDraw(Graphics2D g2d, Palette pallete);
}
