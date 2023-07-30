/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import diamond.model.fold.Face;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceTest1 {
	private static Vertex p0 = new Vertex(new XY(0.0, 0.0));
	private static Vertex p1 = new Vertex(new XY(0.0, 200.0));
	private static Vertex p2 = new Vertex(new XY(-200.0, -200.0));
	private static Vertex p3
			= new Vertex(new XY(-82.84271247461899, 82.84271247461899));
	private static Vertex p4 = new Vertex(new XY(-200.0, 200.0));
	private static Vertex p5 = new Vertex(new XY(-200.0, 0.0));
	private static Vertex p6 = new Vertex(new XY(-200.0, 0.0));
	private static Vertex p7 = new Vertex(new XY(-200.0, -200.0));
	private static Vertex p8
			= new Vertex(new XY(-82.84271247461895, -82.84271247461896));
	private static ArrayList<Vertex> plygon = new ArrayList<Vertex>(
			Arrays.asList(p0, p1, p2, p3, p4, p5, p6, p7, p8));
	private static Face Face = new Face(plygon);

	@Test
	public void testIsInside0() {
		XY c = new XY(-41.421356237309496, 41.421356237309496);
		assertTrue(Face.isInside(c));

	}

}
