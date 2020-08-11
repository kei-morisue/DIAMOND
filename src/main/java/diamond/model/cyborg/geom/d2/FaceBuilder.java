/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceBuilder {
    public static Face square(double width) {
        Face face = new Face();
        face.add(new Vertex(width, width));
        face.add(new Vertex(-width, width));
        face.add(new Vertex(-width, -width));
        face.add(new Vertex(width, -width));
        return face;
    }

    public static Face triangle(Vertex v0, Vertex v1, Vertex v2) {
        Face face = new Face();
        face.add(v0);
        face.add(v1);
        face.add(v2);
        return face;
    }

}
