package diamond.paint.addvertex;

import java.awt.geom.Point2D;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.paint.geometry.GeometricOperation;
import diamond.value.OriLine;

public class AddingVertex extends PickingVertex {

	@Override
	protected void initialize() {

	}

	
	
	@Override
	protected boolean onAct(PaintContext context, Point2D.Double currentPoint,
			boolean freeSelection) {
		
		boolean result = super.onAct(context, currentPoint, true);
		
		if(result == true){
			OriLine line = GeometricOperation.pickLine(
					context);

			if(line != null){
				context.pushLine(line);
			}
			else {
				result = false;
			}
		}
		
		return result;
	}



	@Override
	protected void onResult(PaintContext context) {

		if(context.getVertexCount() > 0){
			
			Doc document = DocHolder.getDoc();
			document.pushUndoInfo();
			CreasePattern creasePattern = document.getCreasePattern();

			Painter painter = new Painter();
			
			if (!painter.addVertexOnLine(
					context.popLine(), context.popVertex(),
					creasePattern, creasePattern.getPaperSize())) {
				DocHolder.getDoc().loadUndoInfo();
			}

		}
		
		context.clear(false);
	}

}
