/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Rectangle2D;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.symbol.Symbol;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class CpClipper {

    public static Rectangle2D.Double clip(Cp cp) {
        ScreenTransform transform = cp.getTransform();
        Rectangle2D.Double r = null;
        for (Symbol<HalfEdge> symbol : cp.getSymbolsHalfEdge().values()) {
            r = clipHeSymbnols(
                    transform,
                    r,
                    symbol,
                    cp);
        }
        for (Symbol<Vertex> symbol : cp.getSymbolsVertex().values()) {
            r = clipVeSymbols(transform, r, symbol);
        }
        if (r == null) {
            return clipAll(r, cp);
        }
        return r;
    }

    private static Rectangle2D.Double clipVeSymbols(ScreenTransform transform,
            Rectangle2D.Double r, Symbol<Vertex> symbol) {
        if (r == null) {
            r = symbol.clip(transform);
        }
        r.add(symbol.clip(transform));
        return r;
    }

    private static Rectangle2D.Double clipHeSymbnols(ScreenTransform transform,
            Rectangle2D.Double r, Symbol<HalfEdge> symbol, Cp cp) {
        HalfEdge he = symbol.getKey();
        Face face = he.getFace();
        if (r == null) {
            r = face.clip(transform);
        }
        r.add(face.clip(transform));

        return r;
    }

    public static Rectangle2D.Double clipAll(Rectangle2D.Double r, Cp cp) {
        ScreenTransform transform = cp.getTransform();
        for (Face f : cp.getFaces()) {
            if (r == null) {
                r = f.clip(transform);
            }
            r.add(f.clip(transform));
        }
        return r;
    }
}
