/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

/**
 * @author Kei Morisue
 *
 */
public class Vertex implements Comparable<Vertex> {
    private double x = .0;
    private double y = .0;

    @Deprecated
    public Vertex() {
    }

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vertex scale(double scale, Vertex v0) {
        Direction d = dir(v0).scale(scale);
        return d.ver(v0);
    }

    public double dist(Vertex v0) {
        return dir(v0).norm();
    }

    public Direction dir(Vertex v0) {
        return new Direction(x - v0.x, y - v0.y);
    }

    public double angle(Vertex v0) {
        return dir(v0).angle();
    }

    public Vertex div(Vertex v0, double p) {
        return dir(v0).scale(p).ver(v0);
    }

    public Vertex c(Vertex v0) {
        return div(v0, .5);
    }

    public double getX() {
        return x;
    }

    @Deprecated

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    @Deprecated

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int compareTo(Vertex v0) {
        if (x < v0.x) {
            return -1;
        }
        if (y < v0.y) {
            return -1;
        }
        if (y > v0.y) {
            return 1;
        }
        return 0;
    }

}
