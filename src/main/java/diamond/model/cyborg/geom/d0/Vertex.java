/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.awt.Graphics2D;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.Graphics;
import diamond.model.cyborg.geom.ShapeBuilder;
import diamond.model.cyborg.style.StyleVertex;
import diamond.model.math.Fuzzy;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Vertex implements Comparable<Vertex>, Cyborg, Graphics {
    private double x = .0;
    private double y = .0;

    @Deprecated
    public Vertex() {
    }

    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double dist(Vertex v0) {
        return dir(v0).norm();
    }

    @Override
    public void draw(Graphics2D g2d, Diagram diagram) {
        double scale = G2DUtil.getScale(g2d);
        g2d.fill(ShapeBuilder.build(this, StyleVertex.SIZE / scale));
    }

    @Override
    public void setG2d(Graphics2D g2d, Diagram diagram) {
        g2d.setColor(StyleVertex.DEFAULT);
    }

    public Vertex scale(double scale, Vertex v0) {
        Direction d = dir(v0).scale(scale);
        return d.ver(v0);
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
        if (Fuzzy.isSmall(dist(v0))) {
            return 0;
        }
        if (x < v0.x) {
            return -1;
        }
        if (y < v0.y) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
    }

}
