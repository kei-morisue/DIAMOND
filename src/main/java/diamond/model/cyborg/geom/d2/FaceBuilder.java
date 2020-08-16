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
        return polygon(new Vertex(width, width),
                new Vertex(-width, width),
                new Vertex(-width, -width),
                new Vertex(width, -width));
    }

    public static Face polygon(Vertex... vs) {
        Face face = new Face();
        for (Vertex v : vs) {
            face.add(v);
        }
        return face;
    }
}
