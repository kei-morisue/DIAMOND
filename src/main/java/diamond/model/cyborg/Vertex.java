/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import diamond.model.Sign;

/**
 * @author Kei Morisue
 *
 */
public class Vertex extends Point2D.Double {
    private LinkedList<HalfEdge> halfEdges = new LinkedList<HalfEdge>();
    private Point2D.Double foldedPosition = new Point2D.Double();

    private boolean isFoldable = false;
    private Sign sign = null;
    private Point2D.Double offset = new Point2D.Double();

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
