/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom;

/**
 * @author Kei Morisue
 *
 */
public class Vertex {
    public double x = .0;
    public double y = .0;
    private double xOff = .0;
    private double yOff = .0;

    public Vertex() {
    }

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vertex(Vertex v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vertex getOffset() {
        return new Vertex(xOff, yOff);
    }

    public void setOffset(Vertex v) {
        this.xOff = v.x;
        this.yOff = v.y;
    }

    public void resetOffset() {
        this.xOff = .0;
        this.yOff = .0;
    }

    public Vertex add(Vertex v) {
        return new Vertex(x + v.x, y + v.y);
    }

    public Vertex neg() {
        return new Vertex(-this.x, -this.y);
    }

    public Vertex scale(double scale) {
        return new Vertex(scale * this.x, scale * this.y);
    }

    public Vertex sub(Vertex v) {
        return this.add(v.neg());
    }

    public double prod(Vertex v) {
        return this.x * v.x + this.y * v.y;
    }

    public double angle(Vertex v) {
        Vertex dir = this.sub(v);
        return Math.atan2(dir.y, dir.x);
    }

    @Deprecated
    public double getX() {
        return x;
    }

    @Deprecated

    public void setX(double x) {
        this.x = x;
    }

    @Deprecated

    public double getY() {
        return y;
    }

    @Deprecated

    public void setY(double y) {
        this.y = y;
    }

    @Deprecated
    public double getxOff() {
        return xOff;
    }

    @Deprecated
    public void setxOff(double xOff) {
        this.xOff = xOff;
    }

    @Deprecated
    public double getyOff() {
        return yOff;
    }

    @Deprecated
    public void setyOff(double yOff) {
        this.yOff = yOff;
    }

}
