/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.step;

import java.util.ArrayList;
import java.util.LinkedList;

import diamond.model.cyborg.diagram.Stars;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.geom.d2.FaceBuilder;
import diamond.model.cyborg.geom.d2.MirrorSimple;

/**
 * @author Kei Morisue
 *
 */
public class StepBuilder {
    static final double A = Math.sqrt(2.0) - 1.0;

    public static Step step0(double size, Stars stars) {
        Step step = new Step(stars);
        Face square = FaceBuilder.square(size, stars);
        LinkedList<Vertex> vs = square.getVertices();
        SegmentCrease e = new SegmentCrease(
                vs.get(0),
                vs.get(2),
                SegmentType.CREASE_VALLEY);
        square.getCreases().add(e);
        step.getFaces().add(square);
        step.update();
        return step;
    }

    public static Step step1(Step step0, Stars stars) {
        Step step = new Step(stars);
        LinkedList<Vertex> vs = step0.getFaces().get(0).getVertices();
        Face t0 = FaceBuilder.triangle(
                vs.get(0),
                vs.get(1),
                vs.get(2),
                stars);
        t0.setMirror(new MirrorSimple(vs.get(0), vs.get(2)));
        Face t1 = FaceBuilder.triangle(
                vs.get(0),
                vs.get(3),
                vs.get(2),
                stars);
        ArrayList<Face> faces = step.getFaces();
        faces.add(t0);
        faces.add(t1);
        step.update();
        return step;
    }

    public static Step craneBase(double size, Stars stars) {
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

        Step step = new Step(stars);
        ArrayList<Face> faces = step.getFaces();
        faces.add(FaceBuilder.triangle(v30, v20, v40, stars));
        faces.add(FaceBuilder.triangle(v30, v41, v10, stars));
        faces.add(FaceBuilder.triangle(v30, v20, v10, stars));
        faces.add(FaceBuilder.triangle(v0, v20, v10, stars));
        faces.add(FaceBuilder.triangle(v20, v40, v0, stars));
        faces.add(FaceBuilder.triangle(v0, v41, v10, stars));
        faces.add(FaceBuilder.triangle(v21, v40, v0, stars));
        faces.add(FaceBuilder.triangle(v0, v41, v11, stars));
        faces.add(FaceBuilder.triangle(v0, v21, v11, stars));
        faces.add(FaceBuilder.triangle(v31, v21, v11, stars));
        faces.add(FaceBuilder.triangle(v31, v21, v40, stars));
        faces.add(FaceBuilder.triangle(v31, v41, v11, stars));
        step.update();
        return step;
    }

}
