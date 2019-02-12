/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.util.LinkedList;

import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.validator.Kawasaki;
import diamond.model.palette.cp.validator.Maekawa;

/**
 * @author long_
 *
 */
public class OriVertex extends AbstractOriVertex {
    public OriVertex(double x, double y) {
        super(x, y);
    }

    public OriVertex(OriPoint point) {
        super(point.x, point.y);
    }

    private LinkedList<OriHalfEdge> halfEdges = new LinkedList<>();
    private boolean isFoldable = false;

    public void setFoldability() {
        isFoldable = Maekawa.isValid(this) && Kawasaki.isValid(this);
    }

    // To store and sort in a clockwise direction
    public void addEdge(OriHalfEdge he) {
        double angle = he.getAngle();
        for (int i = 0; i < halfEdges.size(); i++) {
            double tmpAngle = halfEdges.get(i).getAngle();
            if (tmpAngle > angle) {
                halfEdges.add(i, he);
                return;
            }
        }
        halfEdges.add(he);
        return;
    }

    public OriHalfEdge getPrevEdge(OriHalfEdge e) {
        int index = halfEdges.lastIndexOf(e);
        int eNum = halfEdges.size();
        return halfEdges.get((index - 1 + eNum) % eNum);
    }

    public LinkedList<OriHalfEdge> getHalfEdges() {
        return this.halfEdges;
    }

    public void setEdges(LinkedList<OriHalfEdge> edges) {
        this.halfEdges = edges;
    }

    public boolean isFoldable() {
        return this.isFoldable;
    }

    public void setFoldable(boolean isFoldable) {
        this.isFoldable = isFoldable;
    }

}
