/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.validator.Kawasaki;
import diamond.model.palette.cp.validator.Maekawa;

/**
 * @author long_
 *
 */
public class OriVertex extends AbstractOriVertex {

    private Point2D.Double shearVector = new Point2D.Double(0.0, 0.0);
    private LinkedList<OriHalfEdge> halfEdges = new LinkedList<>();
    private boolean isFoldable = false;
    private Point2D foldedPosition = new Point2D.Double();

    public Point2D getFoldedPosition() {
        return this.foldedPosition;
    }

    public void setFoldedPosition(Point2D foldedPoision) {
        this.foldedPosition = foldedPoision;
    }

    public Point2D.Double getShearVector() {
        return this.shearVector;
    }

    public void setShearVector(Point2D.Double shearVector) {
        this.shearVector = shearVector;
    }

    public OriVertex(double x, double y) {
        super(x, y);
    }

    public OriVertex() {
        super(.0, .0);
    }

    public OriVertex(OriPoint point) {
        super(point.x, point.y);
    }

    public OriVertex add(OriVertex v) {
        return new OriVertex(x + v.x, y + v.y);
    }

    public OriVertex sub(OriVertex v) {
        return new OriVertex(x - v.x, y - v.y);
    }

    public OriVertex scale(double scale) {
        return new OriVertex(x * scale, y * scale);
    }

    public double angle(OriVertex origin) {
        return Math.atan2(y - origin.y, x - origin.x);
    }

    public boolean isAuxVertex() {
        for (OriHalfEdge he : halfEdges) {
            if (he.getType() != LineType.AUX) {
                return false;
            }
        }
        return true;
    }

    public void setFoldability() {
        if (isAuxVertex()) {
            isFoldable = true;
            return;
        }
        if (onCut()) {
            isFoldable = true;
            return;
        }
        if (Maekawa.isValid(this) && Kawasaki.isValid(this)) {
            isFoldable = true;
            return;
        }
        isFoldable = false;
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

    public boolean onCut() {
        for (OriHalfEdge he : halfEdges) {
            if (he.getType() == LineType.CUT) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<OriHalfEdge> getHalfEdges() {
        return this.halfEdges;
    }

    @Deprecated
    public void setHalfEdges(LinkedList<OriHalfEdge> halfEdges) {
        this.halfEdges = halfEdges;
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
