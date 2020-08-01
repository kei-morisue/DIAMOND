/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

/**
 * @author Kei Morisue
 *
 */
public class Direction {
    private double x = .0;
    private double y = .0;

    @Deprecated
    public Direction() {
    }

    public Direction(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vertex ver(Vertex v0) {
        return new Vertex(x + v0.getX(), y + v0.getY());
    }

    public Direction add(Direction v) {
        return new Direction(x + v.x, y + v.y);
    }

    public Direction neg() {
        return new Direction(-this.x, -this.y);
    }

    public Direction scale(double scale) {
        return new Direction(scale * this.x, scale * this.y);
    }

    public Direction scale(double scale, Direction v0) {
        Direction v = this.sub(v0);
        return (new Direction(scale * v.x, scale * v.y)).add(this);
    }

    public Direction sub(Direction v0) {
        return this.add(v0.neg());
    }

    public double prod(Direction v) {
        return this.x * v.x + this.y * v.y;
    }

    public double outer(Direction v) {
        return this.x * v.y - this.y * v.x;
    }

    public double norm() {
        return Math.hypot(x, y);
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public double proj(Direction d) {
        return this.prod(d) / d.prod(d);
    }
}
