/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriFace {
    public ArrayList<OriHalfEdge> halfEdges = new ArrayList<>();
    public boolean selected = false;
    public boolean convex = false;

    public GeneralPath outline = new GeneralPath();
    public GeneralPath preOutline = new GeneralPath();

    public Vector2d getCenter() {
        Vector2d centerVec = new Vector2d();
        for (OriHalfEdge he : halfEdges) {
            centerVec.add(he.vertex.preP);
        }
        centerVec.scale(1.0 / halfEdges.size());
        return centerVec;
    }

    public void makeHalfedgeLoop() {
        int heNum = halfEdges.size();
        for (int i = 0; i < heNum; i++) {
            OriHalfEdge he = halfEdges.get(i);
            he.next = halfEdges.get((i + 1) % heNum);
            he.prev = halfEdges.get((i - 1 + heNum) % heNum);
        }
    }

}
