/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import java.util.LinkedList;

import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.geom.d2.FaceBuilder;
import diamond.model.cyborg.geom.m.MirrorLazy;

/**
 * @author Kei Morisue
 *
 */
public class StepBuilder {
    static final double A = Math.sqrt(2.0) - 1.0;

    public static Step step0(double size) {
        @SuppressWarnings("deprecation")
        Step step = new Step();
        Face square = FaceBuilder.square(size);
        LinkedList<Vertex> vs = square.getVertices();
        SegmentCrease e = new SegmentCrease(
                vs.get(0),
                vs.get(2),
                SegmentType.CREASE_VALLEY);
        square.add(e);
        step.add(square);
        step.update();
        return step;
    }

    public static Step squareBase(Step step0) {
        @SuppressWarnings("deprecation")
        Step step = new Step();
        LinkedList<Vertex> vs = step0.getFaces().get(0).getVertices();
        Vertex c = new Vertex(.0, .0);

        Vertex v0 = vs.get(0);
        Vertex v1 = vs.get(1);
        Vertex v2 = vs.get(2);
        Vertex v3 = vs.get(3);

        Vertex c01 = v0.c(v1);
        Vertex c12 = v1.c(v2);
        Vertex c23 = v2.c(v3);
        Vertex c30 = v3.c(v0);

        Face f0 = FaceBuilder.polygon(c, c01, v1, c12);
        f0.add(new SegmentCrease(c, v1, SegmentType.CREASE));
        Face f2 = FaceBuilder.polygon(c, v0, c01);
        f0.link(f2, c, c01);
        Face f4 = FaceBuilder.polygon(c, c12, v2);
        f0.link(f4, c, c12);
        Face f3 = FaceBuilder.polygon(c, c30, v0);
        Face f5 = FaceBuilder.polygon(c, v2, c23);
        f2.link(f3, c, v0);
        f4.link(f5, c, v2);

        Face f1 = FaceBuilder.polygon(c, c23, v3, c30);
        f1.link(f3, c, c30);
        f1.link(f5, c, c23);

        f1.add(new SegmentCrease(c, v3, SegmentType.CREASE));

        step.add(f0);
        step.add(f2);
        step.add(f4);
        step.add(f3);
        step.add(f5);
        step.add(f1);
        step.update();
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

        @SuppressWarnings("deprecation")
        Step step = new Step();
        step.add(FaceBuilder.polygon(v30, v20, v40));
        step.add(FaceBuilder.polygon(v30, v41, v10));
        step.add(FaceBuilder.polygon(v30, v20, v10));
        step.add(FaceBuilder.polygon(v0, v20, v10));
        step.add(FaceBuilder.polygon(v20, v40, v0));
        step.add(FaceBuilder.polygon(v0, v41, v10));
        step.add(FaceBuilder.polygon(v21, v40, v0));
        step.add(FaceBuilder.polygon(v0, v41, v11));
        step.add(FaceBuilder.polygon(v0, v21, v11));
        step.add(FaceBuilder.polygon(v31, v21, v11));
        step.add(FaceBuilder.polygon(v31, v21, v40));
        step.add(FaceBuilder.polygon(v31, v41, v11));
        for (Face face : step.getFaces()) {
            face.setMirror(new MirrorLazy());//TODO
        }
        step.update();
        return step;
    }

}
