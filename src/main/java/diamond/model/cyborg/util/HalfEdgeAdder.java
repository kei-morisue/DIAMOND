/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeAdder {
    public static void add(Context context, Point2D.Double p0,
            Point2D.Double p1) {
        Cp cp = context.getCp();
        EdgeType type = context.getInputType();
        ArrayList<Face> toBeDeleted = new ArrayList<Face>();
        ArrayList<Face> toBeAdded = new ArrayList<Face>();
        for (Face face : cp.getFaces()) {
            ArrayList<Vertex> crossPoints = CrossPointUtil.getCrossPoints(face,
                    p0, p1);
            if (crossPoints.size() == 2) {
                HalfEdge he = new HalfEdge(crossPoints.get(0),
                        crossPoints.get(1), type);
                Face[] faces = FaceSplitter.split(face, he);
                toBeAdded.add(faces[0]);
                toBeDeleted.add(face);
                if (EdgeType.isSettled(type)) {
                    toBeAdded.add(faces[1]);
                }
            }
        }
        for (Face face : toBeDeleted) {
            cp.remove(face);
        }
        for (Face face : toBeAdded) {
            cp.add(face);
        }
    }
}
