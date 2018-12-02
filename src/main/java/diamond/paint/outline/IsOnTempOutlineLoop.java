package diamond.paint.outline;

import java.util.Collection;

import javax.vecmath.Vector2d;

import diamond.doc.DocHolder;
import diamond.geom.GeomUtil;
import diamond.geom.Line;
import diamond.paint.util.PairLoop;

public class IsOnTempOutlineLoop implements PairLoop.Block<Vector2d> {

	private Vector2d target;
	
	public Vector2d execute(
			Collection<Vector2d> outlineVertices, Vector2d v) {

		target = v;
    	return PairLoop.iterateAll(outlineVertices, this);

	}
	
	@Override
	public boolean yield(Vector2d p0, Vector2d p1) {
		double paperSize = DocHolder.getInstance().getDoc().getPaperSize();

		Line line = new Line(p0, new Vector2d(p1.x - p0.x, p1.y - p0.y));
		
		double distanceToLine = GeomUtil.DistancePointToLine(
        		target, 
        		line);
		
        if (distanceToLine < paperSize * 0.001) {
            return false;
        }

        return true;
	}

}
