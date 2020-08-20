/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.diagram.step;

import diamond.Config;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.geom.d2.FaceBuilder;

/**
 * @author Kei Morisue
 *
 */
public class StepBuilder {
    private static final double a = Config.PAPER_SIZE;
    private static final double b = a * (2.0 - Math.sqrt(2.0));

    private static final Vertex O = new Vertex(.0, .0);

    private static final Vertex L = new Vertex(-a, .0);
    private static final Vertex U = new Vertex(.0, a);
    private static final Vertex B = new Vertex(.0, -a);
    private static final Vertex R = new Vertex(a, .0);

    private static final Vertex UL = new Vertex(-a, a);
    private static final Vertex UR = new Vertex(a, a);
    private static final Vertex BL = new Vertex(-a, -a);
    private static final Vertex BR = new Vertex(a, -a);

    private static final Vertex LL = new Vertex(-b, .0);
    private static final Vertex UU = new Vertex(.0, b);
    private static final Vertex BB = new Vertex(.0, -b);
    private static final Vertex RR = new Vertex(b, .0);

    public static Step step0() {
        @SuppressWarnings("deprecation")
        Step step = new Step();
        Face square = FaceBuilder.polygon(UR, UL, BL, BR);
        SegmentCrease e = new SegmentCrease(
                UR,
                BL,
                SegmentType.CREASE_VALLEY);
        square.add(e);
        step.add(square);
        step.update();
        return step;
    }

    public static Step squareBase() {
        @SuppressWarnings("deprecation")
        Step step = new Step();

        Face f0 = FaceBuilder.polygon(O, L, BL, B);
        f0.add(new SegmentCrease(O, BL, SegmentType.CREASE));
        Face f2 = FaceBuilder.polygon(O, UL, L);
        step.link(f0, f2, O, L);
        Face f4 = FaceBuilder.polygon(O, B, BR);
        step.link(f0, f4, O, B);
        Face f3 = FaceBuilder.polygon(O, U, UL);
        Face f5 = FaceBuilder.polygon(O, BR, R);
        step.link(f2, f3, O, UL);
        step.link(f4, f5, O, BR);

        Face f1 = FaceBuilder.polygon(O, R, UR, U);
        step.link(f1, f3, O, U);
        step.link(f1, f5, O, R);

        f1.add(new SegmentCrease(O, UR, SegmentType.CREASE));

        step.add(f0);
        step.add(f2);
        step.add(f4);
        step.add(f3);
        step.add(f5);
        step.add(f1);
        step.update();
        return step;
    }

    public static Step craneBase() {
        @SuppressWarnings("deprecation")
        Step step = new Step();
        Face f0 = FaceBuilder.polygon(BL, BB, LL);
        Face f1 = FaceBuilder.polygon(BL, BR, BB);
        Face f2 = FaceBuilder.polygon(BL, LL, UL);
        Face f3 = FaceBuilder.polygon(O, LL, BB);
        step.link(f0, f3, LL, BB);
        step.link(f0, f1, BL, BB);
        step.link(f0, f2, BL, LL);
        f1.add(new SegmentCrease(B, BB, SegmentType.CREASE));
        f2.add(new SegmentCrease(L, LL, SegmentType.CREASE));

        Face f4 = FaceBuilder.polygon(BR, UR, RR);
        Face f5 = FaceBuilder.polygon(BR, RR, O);
        Face f6 = FaceBuilder.polygon(BR, O, BB);
        step.link(f1, f6, BB, BR);
        step.link(f3, f6, O, BB);
        step.link(f6, f5, O, BR);
        step.link(f5, f4, BR, RR);
        f4.add(new SegmentCrease(R, RR, SegmentType.CREASE));

        Face f9 = FaceBuilder.polygon(UL, LL, O);
        Face f10 = FaceBuilder.polygon(UL, O, UU);
        Face f11 = FaceBuilder.polygon(UL, UU, UR);
        step.link(f2, f9, LL, UL);
        step.link(f3, f9, O, LL);
        step.link(f9, f10, O, UL);
        step.link(f10, f11, UL, UU);
        f11.add(new SegmentCrease(U, UU, SegmentType.CREASE));

        Face f12 = FaceBuilder.polygon(O, RR, UU);
        Face f14 = FaceBuilder.polygon(UR, UU, RR);
        step.link(f12, f14, UU, RR);
        step.link(f12, f5, O, RR);
        step.link(f12, f0, O, UU);
        step.link(f11, f14, UU, UR);
        step.link(f4, f14, RR, UR);

        step.add(f4);
        step.add(f11);
        step.add(f14);
        step.add(f12);
        step.add(f5);
        step.add(f10);
        step.add(f6);
        step.add(f9);
        step.add(f3);
        step.add(f0);
        step.add(f1);
        step.add(f2);

        step.update();
        return step;
    }

}
