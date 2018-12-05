package diamond.paint.bisector;

import java.awt.geom.Point2D.Double;

import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;

public class SelectingVertexForBisector extends PickingVertex{
	
	private boolean doingFirstAction = true;
	
	public SelectingVertexForBisector(){
		super();
	}


	@Override
	public void onResult(PaintContext context) {
		
	}
	@Override
	protected void initialize() {
		setPreviousClass(this.getClass());
		setNextClass(SelectingLineForBisector.class);
		
//		System.out.println("SelectingFirstVertex.initialize() is called");
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
