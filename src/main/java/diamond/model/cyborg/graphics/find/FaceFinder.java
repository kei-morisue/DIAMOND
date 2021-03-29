/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.util.Collection;
import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class FaceFinder {
    public static <T extends F<T>> Link<T> findEdge(
            List<Link<T>> edges,
            double x, double y, double scale) {
        for (Link<T> edge : edges) {
            if (edge.isNear(x, y, scale)) {
                return edge;
            }
        }
        return null;
    }

    public static <T extends F<T>> Ver<T> findVer(
            List<Link<T>> edges,
            double x,
            double y,
            double scale) {
        for (Link<T> edge : edges) {
            Ver<T> ver = edge.findVer(x, y, scale);
            if (ver != null) {
                return ver;
            }
        }
        return null;
    }

    public static <T extends F<T>> Seg<T> findCrease(
            Collection<Seg<T>> creases,
            double x,
            double y,
            double scale) {
        for (Seg<T> crease : creases) {
            if (crease.isNear(x, y, scale)) {
                return crease;
            }
        }
        return null;
    }
}
