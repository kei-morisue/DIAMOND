/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * @author Kei Morisue
 *
 */
public class StringDrawer {
	private static final Color COLOR = Color.BLACK;
	final public static Font FONT_FOLDED_STEP = new Font("Arial", Font.BOLD,
			100);
	final public static Font FONT_REPEAT_STEP = new Font("Arial", Font.PLAIN,
			50);
	final public static Font FONT_DIAGRAM_STEP = new Font("Arial", Font.BOLD,
			40);
	final public static Font FONT_TUTORIAL_STEP = new Font(
			"Arial",
			Font.BOLD,
			200);

	public static void drawStepNo(
			Graphics2D g2d,
			int step,
			int x,
			int y) {
		g2d.setFont(FONT_FOLDED_STEP);
		g2d.setColor(COLOR);
		draw(g2d, step, x, y);
	}

	private static void draw(
			Graphics2D g2d,
			int step,
			int x,
			int y) {
		AffineTransform tmpTransform = g2d.getTransform();
		g2d.setTransform(new AffineTransform());
		g2d.drawString(String.valueOf(step), 10 + x,
				g2d.getFont().getSize() + y);
		g2d.setTransform(tmpTransform);
	}

}