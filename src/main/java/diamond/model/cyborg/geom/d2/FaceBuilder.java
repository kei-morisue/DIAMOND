/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.LinkedList;

import diamond.model.cyborg.diagram.Stars;
import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceBuilder {
    public static Face square(double width, Stars stars) {
        Face face = new Face();
        LinkedList<Vertex> vertices = face.getVertices();
        vertices.add(new Vertex(width, width));
        vertices.add(new Vertex(-width, width));
        vertices.add(new Vertex(-width, -width));
        vertices.add(new Vertex(width, -width));
        stars.addAll(vertices);
        return face;
    }

    public static Face triangle(Vertex v0, Vertex v1, Vertex v2, Stars stars) {
        Face face = new Face();
        LinkedList<Vertex> vertices = face.getVertices();
        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        stars.addAll(vertices);
        return face;
    }

}
