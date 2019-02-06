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
    public Vector2d p = new Vector2d();
    public Vector2d preP = new Vector2d();
    public Vector2d tmpVec = new Vector2d();
    public LinkedList<OriEdge> edges = new LinkedList<>();
    public boolean isFoldable = false;

    public OriVertex(Vector2d p) {
        this.p.set(p);
        preP.set(p);
    }

    public OriVertex(double x, double y) {
        p.set(x, y);
        preP.set(p);
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
        if (edge.sv == this) {
            dir.set(edge.ev.p.x - this.p.x, edge.ev.p.y - this.p.y);
        } else {
            dir.set(edge.sv.p.x - this.p.x, edge.sv.p.y - this.p.y);
        }
        return Math.atan2(dir.y, dir.x);
    }

    public OriEdge getPrevEdge(OriEdge e) {
        int index = edges.lastIndexOf(e);
        int eNum = edges.size();
        return edges.get((index - 1 + eNum) % eNum);
    }
}
