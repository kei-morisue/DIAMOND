/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import java.util.LinkedList;

/**
 * @author Kei Morisue
 *
 */
public class FaceBuilder {
    public static Face square(double width) {
        Face face = new Face();
        LinkedList<Vertex> vertices = face.getVertices();
        vertices.add(new Vertex(width, width));
        vertices.add(new Vertex(-width, width));
        vertices.add(new Vertex(-width, -width));
        vertices.add(new Vertex(width, -width));
        return face;
    }

    public static Face triangle(Vertex v0, Vertex v1, Vertex v2) {
        Face face = new Face();
        LinkedList<Vertex> vertices = face.getVertices();
        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        return face;
    }

}
