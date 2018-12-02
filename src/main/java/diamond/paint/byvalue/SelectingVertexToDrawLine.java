package diamond.paint.byvalue;

import javax.vecmath.Vector2d;

import diamond.doc.DocHolder;
import diamond.geom.GeomUtil;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.value.OriLine;

public class SelectingVertexToDrawLine extends PickingVertex {

	@Override
	protected void initialize() {

	}

	@Override
	protected void onResult(PaintContext context) {
		Vector2d vertex = context.getVertex(0);

		double length;
		double angle;
		try {
			ValueDB valDB = ValueDB.getInstance();
			length = valDB.getLength();
			angle = valDB.getAngle();


			if (length > 0) {
				OriLine vl = GeomUtil.getLineByValue(vertex, length, -angle, PaintConfig.inputLineType);

				DocHolder.getInstance().getDoc().pushUndoInfo();
				DocHolder.getInstance().getDoc().addLine(vl);
			}
		} 
		catch (Exception ex) {
		}

		context.clear(false);
	}


}
