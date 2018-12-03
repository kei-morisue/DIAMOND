package diamond.paint.segment;

import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;


public class SelectingFirstVertexForSegment extends PickingVertex{


	
	
	public SelectingFirstVertexForSegment(){
		super();
	}
	
	@Override
	public void onResult(PaintContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undoAction(PaintContext context) {
		context.clear(false);
	}

	@Override
	protected void initialize() {
		setPreviousClass(this.getClass());
		setNextClass(SelectingSecondVertexForSegment.class);
		
//		System.out.println("SelectingFirstVertex.initialize() is called");
	}
	
}
