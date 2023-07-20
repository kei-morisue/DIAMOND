/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashSet;

import diamond.controller.Context;
import diamond.model.cyborg.Vertex;
import diamond.model.symbol.Symbol;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class OffsetUtil {
	private static Double getP(Context context) {
		AbstractScreen paintScreen = context.getPaintScreen();
		double w = paintScreen.getWidth();
		double h = paintScreen.getHeight();
		Double mousePoint = context.getMousePoint();
		Double p = Point2DUtil.sub(mousePoint, new Point2D.Double(w * 0.5, h * 0.5));
		return p;
	}

	private static Double getParam2D(Context context) {
		AbstractScreen paintScreen = context.getPaintScreen();
		double w = paintScreen.getWidth();
		double h = paintScreen.getHeight();
		Double mousePoint = context.getMousePoint();
		Double p = new Point2D.Double(mousePoint.x / w, mousePoint.y / h);
		return p;
	}

	public static void offset(Context context) {
		Double p = getParam2D(context);
		HashSet<Vertex> vertices = context.getCp().getVertices();
		double k = Math.sqrt(vertices.size());
		vertices.forEach(vertex -> {
			double phi = p.y * Math.PI / k;
			double t = 1 + p.x / k;
			double s = Math.sin(phi);
			double c = Math.cos(phi);
			double x = vertex.x;
			double y = vertex.y;
			double dx = x * (t * c - 1) - y * t * s;
			double dy = y * (t * c - 1) + x * t * s;
			vertex.setOffset(new Point2D.Double(dx, dy));
		});
	}

	public static void setOffset(Context context, Vertex vertex) {
		Double p = getP(context);
		ScreenTransform transform = context.getFoldedScreen().getTransform();
		double theta = transform.getTheta();
		double scale = transform.getScale();
		vertex.setOffset(Point2DUtil.rotate(Point2DUtil.scale(p, 1.0 / scale), -theta));
	}

	public static <T> void setOffset(Context context, Symbol<T> symbol) {
		Double p = getP(context);
		ScreenTransform transform = context.getFoldedScreen().getTransform();
		double theta = transform.getTheta();
		double scale = transform.getScale();
		symbol.setOffset(Point2DUtil.rotate(Point2DUtil.scale(p, 1.0 / scale), -theta));
	}

	public static <T> void setScale(Context context, Symbol<T> symbol) {
		Double p = getP(context);
		ScreenTransform transform = context.getFoldedScreen().getTransform();
		double scale = transform.getScale();
		AbstractScreen paintScreen = context.getPaintScreen();
		int w = paintScreen.getWidth();
		int h = paintScreen.getHeight();
		double hypot = Math.hypot(w, h) * 0.5;
		symbol.setScale(Point2DUtil.norm(p) / scale / hypot);
	}

}
