/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.util.LinkedList;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriVertex {
    private Vector2d p = new Vector2d();
    private LinkedList<OriEdge> edges = new LinkedList<>();
    private boolean isFoldable = false;

    private boolean tmpFlg = false;

    public OriVertex(Vector2d p) {
        this.p.set(p);
    }

    public OriVertex(double x, double y) {
        p.set(x, y);
    }

    // To store and sort in a clockwise direction
    public void addEdge(OriEdge edge) {
        double angle = getAngle(edge);
        boolean added = false;
        for (int i = 0; i < edges.size(); i++) {
            double tmpAngle = getAngle(edges.get(i));
            if (tmpAngle > angle) {
                edges.add(i, edge);
                added = true;
                break;
            }
        }
        if (!added) {
            edges.add(edge);
        }
    }

    private double getAngle(OriEdge edge) {
        Vector2d dir = new Vector2d();
        if (edge.isSv(this)) {
            double dx = edge.getEv().p.x - this.p.x;
            double dy = edge.getEv().p.y - this.p.y;
            dir.set(dx, dy);
        } else {
            double dx = edge.getSv().p.x - this.p.x;
            double dy = edge.getSv().p.y - this.p.y;
            dir.set(dx, dy);
        }
        return Math.atan2(dir.y, dir.x);
    }

    public OriEdge getPrevEdge(OriEdge e) {
        int index = edges.lastIndexOf(e);
        int eNum = edges.size();
        return edges.get((index - 1 + eNum) % eNum);
    }

    public Vector2d getP() {
        return this.p;
    }

    public void setP(OriVertex p) {
        this.p = getP();
    }

    public LinkedList<OriEdge> getEdges() {
        return this.edges;
    }

    public void setEdges(LinkedList<OriEdge> edges) {
        this.edges = edges;
    }

    public boolean isFoldable() {
        return this.isFoldable;
    }

    public void setFoldable(boolean isFoldable) {
        this.isFoldable = isFoldable;
    }

    @Override
    public String toString() {
        return "(" + p.x + ", " + p.y + ")";
    }

    public boolean isTmpFlg() {
        return this.tmpFlg;
    }

    public void setTmpFlg(boolean tmpFlg) {
        this.tmpFlg = tmpFlg;
    }
}
