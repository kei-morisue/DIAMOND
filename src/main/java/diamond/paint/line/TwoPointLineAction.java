package diamond.paint.line;

import diamond.paint.core.PaintContext;
import diamond.paint.segment.TwoPointSegmentAction;

public class TwoPointLineAction extends TwoPointSegmentAction {


	public TwoPointLineAction(){
		setActionState(new SelectingFirstVertexForLine());
	}

	
	
	@Override
	public void destroy(PaintContext context) {
		// TODO Auto-generated method stub
		super.destroy(context);
	}



	@Override
	public void recover(PaintContext context) {
		setActionState(new SelectingFirstVertexForLine());
		
	}
	
	

}
