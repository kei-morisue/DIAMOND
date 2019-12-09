/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.LinkedList;

import diamond.Config;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class CpBuilder {
    public static void buildSquare(LinkedList<Face> faces) {
        double size = Config.PAPER_SIZE;
        Vertex v0 = new Vertex(size, size);
        Vertex v1 = new Vertex(-size, size);
        Vertex v2 = new Vertex(-size, -size);
        Vertex v3 = new Vertex(size, -size);

        HalfEdge he0 = new HalfEdge(v0, v1, EdgeType.CUT);
        HalfEdge he1 = new HalfEdge(v1, v2, EdgeType.CUT);
        HalfEdge he2 = new HalfEdge(v2, v3, EdgeType.CUT);
        HalfEdge he3 = new HalfEdge(v3, v0, EdgeType.CUT);

        Face f0 = new Face();
        faces.add(f0);
        f0.add(he0);
        f0.add(he1);
        f0.add(he2);
        f0.add(he3);

        he0.connectTo(he1);
        he1.connectTo(he2);
        he2.connectTo(he3);
        he3.connectTo(he0);

        he0.getPair().connectTo(he3.getPair());
        he3.getPair().connectTo(he2.getPair());
        he2.getPair().connectTo(he1.getPair());
        he1.getPair().connectTo(he0.getPair());

    }

    public static Cp buildNext(Cp cp) {
        Cp cp2 = new Cp();
        cp2.setTransform(new ScreenTransform(cp.getTransform()));
        return cp2;//TODO
    }
}
