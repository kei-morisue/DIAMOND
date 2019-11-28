/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.Point2D;
import java.util.LinkedList;

/**
 * @author Kei Morisue
 *
 */
public class Vertex extends Point2D.Double implements Cyborg {
    private LinkedList<HalfEdge> halfEdges = new LinkedList<HalfEdge>();
    private VertexProperty property = new VertexProperty();
    private Point2D.Double offset = new Point2D.Double();
    private Point2D.Double folded = null;

    public Vertex() {
    }

    public Vertex(double x, double y) {
        super(x, y);
    }

    public Vertex(Point2D.Double p) {
        super(p.x, p.y);
    }

    public void add(HalfEdge he) {
        double angle = he.dir();
        for (int i = 0; i < halfEdges.size(); i++) {
            double tmpAngle = halfEdges.get(i).dir();
            if (tmpAngle > angle) {
                halfEdges.add(i, he);
                return;
            }
        }
        halfEdges.add(he);
        return;
    }

    public LinkedList<HalfEdge> getHalfEdges() {
        return halfEdges;
    }

    public void setHalfEdges(LinkedList<HalfEdge> halfEdges) {
        this.halfEdges = halfEdges;
    }

    public VertexProperty getProperty() {
        return property;
    }

    public void setProperty(VertexProperty property) {
        this.property = property;
    }

    public Point2D.Double getOffset() {
        return offset;
    }

    public void setOffset(Point2D.Double offset) {
        this.offset = offset;
    }

    public Point2D.Double getFolded() {
        return folded;
    }

    public void setFolded(Point2D.Double folded) {
        this.folded = folded;
    }
}
