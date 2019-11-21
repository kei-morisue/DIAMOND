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
public class Vertex extends Point2D.Double {
    private LinkedList<HalfEdge> halfEdges = new LinkedList<HalfEdge>();
    public VertexProperty property = new VertexProperty();

    public Vertex(double x, double y) {
        super(x, y);
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
}
