/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

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
        Double p = Point2DUtil.sub(context.getMousePoint(),
                new Point2D.Double(w * 0.5, h * 0.5));
        return p;
    }

    public static void setOffset(Context context, Vertex vertex) {
        Double p = getP(context);
        ScreenTransform transform = context.getFoldedScreen().getTransform();
        double theta = transform.getTheta();
        double scale = transform.getScale();
        vertex.setOffset(
                Point2DUtil.rotate(Point2DUtil.scale(p, 1.0 / scale), -theta));
    }

    public static <T> void setOffset(Context context, Symbol<T> symbol) {
        Double p = getP(context);
        ScreenTransform transform = context.getFoldedScreen().getTransform();
        double theta = transform.getTheta();
        double scale = transform.getScale();
        symbol.setOffset(
                Point2DUtil.rotate(Point2DUtil.scale(p, 1.0 / scale), -theta));
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
