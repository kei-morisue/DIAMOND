/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Kei Morisue
 *
 */
public class GeoTest {
	private static XY a = new XY(-1.0, 0.0);
	private static XY b = new XY(1.0, 0.0);
	private static ArrayList<XY> p = new ArrayList<XY>();
	private static ArrayList<XY> q = new ArrayList<XY>();

	@Test
	public void testIntersect0() {
		XY c = new XY(1.0, 1.0);
		XY d = new XY(2.0, 3.0);
		double eps = getEps(c, d);
		XY ix = Geo.intersect(a, b, c, d, eps);
		assertEquals(null, ix);
	}

	@Test
	public void testIntersect1() {
		XY c = new XY(0.0, -0.5);
		XY d = new XY(1.0, 0.5);
		double eps = getEps(c, d);
		XY ix = Geo.intersect(a, b, c, d, eps);
		assert (Geo.close(new XY(0.5, 0.0), ix, eps));
	}

	@Test
	public void testIntersect2() {
		XY c = new XY(1.0, 0.5);
		XY d = new XY(2.0, 1.5);
		double eps = getEps(c, d);
		XY ix = Geo.intersect(a, b, c, d, eps);
		assertEquals(null, ix);
	}

	@Test
	public void testIntersect3() {
		XY o = new XY(0.0, 0.0);
		int samples = 100;
		for (int i = 0; i < samples; i++) {
			XY r = new XY(Math.random(), Math.random());
			if (Geo.isZero(r.getY())) {
				continue;
			}
			XY c = o.mid(r, 2);
			XY d = o.mid(r, -2);
			double eps = getEps(c, d);
			XY ix = Geo.intersect(a, b, c, d, eps);
			assert (Geo.close(o, ix, eps));
		}

	}

	private double getEps(XY c, XY d) {
		p.add(a);
		p.add(c);
		q.add(b);
		q.add(d);
		double eps = Geo.minLength(p, q);
		return eps / 300;
	}

}
