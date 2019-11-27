/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.LinkedList;
import java.util.Set;

import diamond.Config;

/**
 * @author Kei Morisue
 *
 */
public class CpBuilder {
    public static void buildSquare(LinkedList<Face> faces, Set<Vertex> vertices,
            Set<HalfEdge> hes) {
        double size = Config.PAPER_SIZE;
        Vertex v0 = new Vertex(size, size);
        Vertex v1 = new Vertex(-size, size);
        Vertex v2 = new Vertex(-size, -size);
        Vertex v3 = new Vertex(size, -size);
        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        HalfEdge he0 = new HalfEdge(v0, v1, EdgeType.CUT);
        HalfEdge he1 = new HalfEdge(v1, v2, EdgeType.CUT);
        HalfEdge he2 = new HalfEdge(v2, v3, EdgeType.CUT);
        HalfEdge he3 = new HalfEdge(v3, v0, EdgeType.CUT);
        hes.add(he0);
        hes.add(he1);
        hes.add(he2);
        hes.add(he3);

        Face f0 = new Face();
        faces.add(f0);
        f0.open(he0);
        f0.add(he1);
        f0.add(he2);
        f0.close(he3);

        //        HalfEdge he0P = he0.getPair();
        //        HalfEdge he1P = he1.getPair();
        //        HalfEdge he2P = he2.getPair();
        //        HalfEdge he3P = he3.getPair();
        //        hes.add(he0P);
        //        hes.add(he1P);
        //        hes.add(he2P);
        //        hes.add(he3P);
        //
        //        Face f1 = new Face();
        //        faces.add(f1);
        //        f1.open(he0P);
        //        f1.add(he1P);
        //        f1.add(he2P);
        //        f1.close(he3P);
        //        f1.getProperty().setDisabled(true);
    }
}
