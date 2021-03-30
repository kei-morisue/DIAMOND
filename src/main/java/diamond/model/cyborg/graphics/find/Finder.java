/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.util.Collection;

/**
 * @author Kei Morisue
 *
 */
public class Finder {
    public static <T extends Metric> T find(
            Collection<T> candidates,
            double x,
            double y,
            double scale) {
        for (T candidate : candidates) {
            if (candidate.isNear(x, y, scale)) {
                return candidate;
            }
        }
        return null;
    }

    @SafeVarargs
    public static <T extends Metric> T find(
            double x,
            double y,
            double scale,
            T... candidates) {
        for (T candidate : candidates) {
            if (candidate.isNear(x, y, scale)) {
                return candidate;
            }
        }
        return null;
    }

    public static <T extends Metric> boolean find(
            double x,
            double y,
            double scale,
            T candidate) {
        return candidate.isNear(x, y, scale);
    }
}
