package diamond.paint.symmetric;

import java.awt.geom.Point2D.Double;
import java.util.Collection;

import javax.vecmath.Vector2d;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class SelectingVertexForSymmetric extends PickingVertex{
	
	private boolean doingFirstAction = true;
	
	private boolean doSpecial = false;


	public SelectingVertexForSymmetric(){
		super();
	}
	
	@Override
	public void onResult(PaintContext context) {
		Doc document = DocHolder.getDoc();
		Collection<OriLine> creasePattern = document.getCreasePattern();

		document.pushCachedUndoInfo();
		
		Vector2d first = context.getVertex(0);
		Vector2d second = context.getVertex(1);
		Vector2d third = context.getVertex(2);
		
		Painter painter = new Painter();

		if (doSpecial) {
			painter.addSymmetricLineAutoWalk(
					first, second, third, first, creasePattern);
		} else {
			painter.addSymmetricLine(
					first, second, third, creasePattern);
		}

		doingFirstAction = true;
        context.clear(false);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected boolean onAct(PaintContext context, Double currentPoint,
			boolean doSpecial) {
		
		if(doingFirstAction){
			DocHolder.getDoc().cacheUndoInfo();
			doingFirstAction = false;
		}
		
		boolean result = super.onAct(context, currentPoint, doSpecial);
		
		if(result == true){
			if(context.getVertexCount() < 3){
				result = false;
			}
		}

		this.doSpecial = doSpecial;
		
		return result;
	}

	
}
