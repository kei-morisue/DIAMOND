/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import diamond.model.cyborg.util.Point2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Vertex extends Point2D.Double implements Cyborg {
    private LinkedList<HalfEdge> halfEdges = new LinkedList<HalfEdge>();
    private VertexProperty property = new VertexProperty();
    private Point2D.Double offset = new Point2D.Double();
    private Point2D.Double folded = new Point2D.Double();

    public Vertex() {
    }

    public Vertex(double x, double y) {
        super(x, y);
    }

    public Vertex(Point2D.Double p) {
        super(p.x, p.y);
    }

    public Vertex(Vertex vertex) {
        super(vertex.x, vertex.y);
        this.offset = vertex.offset;
    }

    public Point2D.Double getFoldedOffset() {
        return Point2DUtil.add(folded, offset);
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

    public void remove(HalfEdge he) {
        halfEdges.remove(he);
    }

    public HalfEdge getConnection(Vertex v) {
        for (HalfEdge he : halfEdges) {
            if (v == he.getV1()) {
                return he;
            }
        }
        return null;
    }

    public boolean isVertex() {
        for (HalfEdge he : halfEdges) {
            if (EdgeType.isSettled(he.getType())) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<HalfEdge> getHalfEdges() {
        return halfEdges;
    }

    public VertexProperty getProperty() {
        return property;
    }

    @Deprecated
    public void setProperty(VertexProperty property) {
        this.property = property;
    }

    @Deprecated
    public Point2D.Double getOffset() {
        return offset;
    }

    public void setOffset(Point2D.Double offset) {
        this.offset = offset;
    }

    @Deprecated
    public Point2D.Double getFolded() {
        return folded;
    }

    @Deprecated
    public void setFolded(Point2D.Double folded) {
        this.folded = folded;
    }

    @Override
    public java.awt.geom.Rectangle2D.Double clip() {
        return new Rectangle2D.Double(x, y, 0, 0);
    }
}
