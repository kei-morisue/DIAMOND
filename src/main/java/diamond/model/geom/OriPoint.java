/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.model.geom;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriPoint extends Vector2d implements Comparable<OriPoint> {
    public OriPoint(Double x, Double y) {
        super(x, y);
    }

    @Override
    public int compareTo(OriPoint o) {
        if (this.x == o.x) {
            return (int) Math.signum(this.y - o.y);
        }
        return (int) Math.signum(this.x - o.x);
    }

}
