/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeSplitter {
    public static Vertex split(HalfEdge he, double t) {
        Double p = Point2DUtil.split(he.getV0(), he.getV1(), t);
        Vertex v = new Vertex(p);
        HalfEdge h0 = new HalfEdge(he.getV0(), v, he.getType());
        HalfEdge h1 = new HalfEdge(v, he.getV1(), he.getType());
        he.getPrev().connectTo(h0);
        h0.connectTo(h1);
        h1.connectTo(he.getNext());
        h0.setFace(he.getFace());
        h1.setFace(he.getFace());

        ArrayList<HalfEdge> halfEdges0 = he.getFace().getHalfEdges();
        int i = halfEdges0.indexOf(he);
        halfEdges0.add(i, h0);
        halfEdges0.add(i + 1, h1);
        halfEdges0.remove(i + 2);

        HalfEdge h0P = h0.getPair();
        HalfEdge h1P = h1.getPair();
        HalfEdge heP = he.getPair();

        heP.getPrev().connectTo(h1P);
        h1P.connectTo(h0P);
        h0P.connectTo(heP.getNext());

        h0P.setFace(heP.getFace());
        h1P.setFace(heP.getFace());

        ArrayList<HalfEdge> halfEdges1 = heP.getFace().getHalfEdges();
        int j = halfEdges1.indexOf(heP);
        halfEdges1.add(j, h1.getPair());
        halfEdges1.add(j + 1, h0.getPair());
        halfEdges1.remove(j + 2);
        return v;
    }
}
