package diamond.paint.geometry;

import javax.vecmath.Vector2d;

public class NearestPoint {
	public double distance = Double.MAX_VALUE;
	public Vector2d point = new Vector2d();

	/**
	 * distance is set to maximum. point is nut null but dummy.
	 */
	public NearestPoint() {
	}
	
	public NearestPoint(NearestPoint p) {
		if(p != null){
			point.set(p.point);
			distance = p.distance;
		}
	}
}
