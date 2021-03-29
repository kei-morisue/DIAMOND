/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class StepFinder {
    public static <T extends F<T>> D1<T> findEdge(
            List<Face<T>> faces,
            double x,
            double y,
            double scale) {
        for (Face<T> face : faces) {
            Link<T> link = face.findEdge(x, y, scale);
            if (link != null) {
                return link;
            }
        }
        return null;
    }

    public static <T extends F<T>> Seg<T> findCrease(
            List<Face<T>> faces,
            double x,
            double y,
            double scale) {
        for (Face<T> face : faces) {
            Seg<T> seg = face.findCrease(x, y, scale);
            if (seg != null) {
                return seg;
            }
        }
        return null;
    }

    public static <T extends F<T>> Ver<T> findVer(
            List<Face<T>> faces,
            double x,
            double y,
            double scale) {
        for (Face<T> face : faces) {
            Ver<T> ver = face.findVer(x, y, scale);
            if (ver != null) {
                return ver;
            }
        }
        return null;
    }

    public static <T extends F<T>> Face<T> findFace(
            List<Face<T>> faces,
            double x,
            double y,
            double scale) {
        return faces.get(0);//TODO
    }

}
