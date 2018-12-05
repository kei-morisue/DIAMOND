package diamond.paint.byvalue;

import java.awt.geom.Point2D.Double;

import diamond.doc.DocHolder;
import diamond.geom.GeomUtil;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;

public class SelectingVertexForLength extends PickingVertex{
	
	private boolean doingFirstAction = true;
	
	public SelectingVertexForLength(){
		super();
	}


	@Override
	public void onResult(PaintContext context) {

        double length = GeomUtil.Distance(
        		context.getVertex(0), context.getVertex(1));

        ValueDB valDB = ValueDB.getInstance();
        valDB.setLength(length);
        valDB.notifyObservers();

//        Globals.subLineInputMode = Constants.SubLineInputMode.NONE;

		
        doingFirstAction = true;
        context.clear(false);

        context.setMissionCompleted(true);
	}
	@Override
	protected void initialize() {
	}

	@Override
	protected boolean onAct(PaintContext context, Double currentPoint,
			boolean doSpecial) {
		
		context.setMissionCompleted(false);
		
		if(doingFirstAction){
			DocHolder.getDoc().cacheUndoInfo();
			doingFirstAction = false;
		}
		
		boolean result = super.onAct(context, currentPoint, doSpecial);
		
		if(result == true){
			if(context.getVertexCount() < 2){
				result = false;
			}
		}
		
		return result;
	}

	
}
