/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.step;

import java.util.ArrayList;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.geom.d2.FaceBuilder;

/**
 * @author Kei Morisue
 *
 */
public class StepBuilder {
    static final double A = Math.sqrt(2.0) - 1.0;

    public static Step step0(double size) {
        Step step = new Step();
        step.getFaces().add(FaceBuilder.square(size));
        return step;
    }

    public static Step craneBase(double size) {
        double b = A * size;
        Vertex v0 = new Vertex(.0, -b);
        Vertex v10 = new Vertex(-b, .0);
        Vertex v11 = new Vertex(-b, .0);
        Vertex v20 = new Vertex(b, .0);
        Vertex v21 = new Vertex(b, .0);
        Vertex v30 = new Vertex(.0, -size);
        Vertex v31 = new Vertex(.0, -size);
        Vertex v40 = new Vertex(.0, size);
        Vertex v41 = new Vertex(.0, size);

        Step step = new Step();
        ArrayList<Face> faces = step.getFaces();
        faces.add(FaceBuilder.triangle(v30, v20, v40));
        faces.add(FaceBuilder.triangle(v30, v41, v10));
        faces.add(FaceBuilder.triangle(v30, v20, v10).flip());
        faces.add(FaceBuilder.triangle(v0, v20, v10));
        faces.add(FaceBuilder.triangle(v20, v40, v0));
        faces.add(FaceBuilder.triangle(v0, v41, v10).flip());
        faces.add(FaceBuilder.triangle(v21, v40, v0).flip());
        faces.add(FaceBuilder.triangle(v0, v41, v11));
        faces.add(FaceBuilder.triangle(v0, v21, v11).flip());
        faces.add(FaceBuilder.triangle(v31, v21, v11));
        faces.add(FaceBuilder.triangle(v31, v21, v40).flip());
        faces.add(FaceBuilder.triangle(v31, v41, v11).flip());

        return step;
    }

}
