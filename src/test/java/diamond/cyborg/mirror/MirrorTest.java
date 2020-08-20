/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.cyborg.mirror;

import static org.junit.Assert.*;

import java.awt.geom.AffineTransform;

import org.junit.Test;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d1.SegmentType;
import diamond.model.cyborg.geom.m.MirrorPlain;
import diamond.model.math.Constants;

/**
 * @author Kei Morisue
 *
 */
public class MirrorTest {
    private final static double l = 100.0;
    private static double s0 = 0.34;
    private static double x0 = 252.44;
    private static double y0 = 424.676;
    private static double s1 = s0 + Math.PI;
    private static double x1 = x0 + l * Math.cos(s0);
    private static double y1 = y0 + l * Math.sin(s0);

    private final static double xEx = x0
            - (x0 * Math.cos(-2 * s0) - y0 * Math.sin(-2 * s0));
    private final static double yEx = y0
            + (x0 * Math.sin(-2 * s0) + y0 * Math.cos(-2 * s0));

    @Test
    public void affine0Test() {
        AffineTransform affine = new AffineTransform();
        affine.concatenate(AffineTransform.getTranslateInstance(x0, y0));
        affine.concatenate(AffineTransform.getRotateInstance(s0));
        affine.concatenate(AffineTransform.getScaleInstance(1, -1));
        affine.concatenate(AffineTransform.getRotateInstance(-s0));
        affine.concatenate(AffineTransform.getTranslateInstance(-x0, -y0));
        assertEquals(xEx, affine.getTranslateX(), Constants.EPS);
        assertEquals(yEx, affine.getTranslateY(), Constants.EPS);
    }

    @Test
    public void affine1Test() {
        AffineTransform affine = new AffineTransform();
        affine.concatenate(AffineTransform.getTranslateInstance(x1, y1));
        affine.concatenate(AffineTransform.getRotateInstance(s1));
        affine.concatenate(AffineTransform.getScaleInstance(1, -1));
        affine.concatenate(AffineTransform.getRotateInstance(-s1));
        affine.concatenate(AffineTransform.getTranslateInstance(-x1, -y1));
        assertEquals(xEx, affine.getTranslateX(), Constants.EPS);
        assertEquals(yEx, affine.getTranslateY(), Constants.EPS);
    }

    @Test
    public void v1Test() {
        assertEquals(xEx, x1
                - (x1 * Math.cos(-2 * s1) - y1 * Math.sin(-2 * s1)),
                Constants.EPS);
        assertEquals(yEx, y1
                + (x1 * Math.sin(-2 * s1) + y1 * Math.cos(-2 * s1)),
                Constants.EPS);
    }

    @Test
    public void mirror0Test() {
        Vertex v0 = new Vertex(x0, y0);
        Direction d = new Direction(Math.cos(s0), Math.sin(s0)).scale(l);
        mirrorTest(v0, d);
    }

    @Test
    public void mirror1Test() {
        Vertex v0 = new Vertex(x1, y1);
        Direction d = new Direction(Math.cos(s1), Math.sin(s1)).scale(l);
        mirrorTest(v0, d);
    }

    private void mirrorTest(Vertex v0, Direction d) {
        Vertex v1 = d.ver(v0);
        MirrorPlain m = new MirrorPlain(
                new SegmentCrease(v0, v1, SegmentType.CREASE));
        assertEquals(xEx, m.getB().getX(), Constants.EPS);
        assertEquals(yEx, m.getB().getY(), Constants.EPS);
    }

}
