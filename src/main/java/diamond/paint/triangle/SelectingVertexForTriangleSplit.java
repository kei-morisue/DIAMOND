package diamond.paint.triangle;

import java.awt.geom.Point2D.Double;
import java.util.Collection;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class SelectingVertexForTriangleSplit extends PickingVertex{
	
	private boolean doingFirstAction = true;
	
	public SelectingVertexForTriangleSplit(){
		super();
	}


	@Override
	public void onResult(PaintContext context) {
		Doc document = DocHolder.getDoc();
		Collection<OriLine> creasePattern = document.getCreasePattern();
		
		document.pushCachedUndoInfo();

		Painter painter = new Painter();
		painter.addTriangleDivideLines(
				context.getVertex(0), context.getVertex(1), context.getVertex(2),
				creasePattern);

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
		
		return result;
	}

	
}
