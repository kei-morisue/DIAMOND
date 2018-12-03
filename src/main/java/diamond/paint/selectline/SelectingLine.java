package diamond.paint.selectline;

import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingLine;
import diamond.value.OriLine;

public class SelectingLine extends PickingLine {

	
	
	public SelectingLine() {
		super();
	}

	@Override
	protected void initialize() {
	}

	
	
	
	@Override
	protected void onResult(PaintContext context) {
		
		DocHolder.getInstance().getDoc().pushUndoInfo();
		
		
		OriLine line = context.peekLine();

		// toggle selection
		if(line.selected){
	    	line.selected = false;
	    	context.popLine();
	    	// line should be already stored.
	    	context.removeLine(line);
	    }
	    else {
	    	line.selected = true;
	    }

	}

	@Override
	protected void undoAction(PaintContext context) {
		// TODO Auto-generated method stub
		super.undoAction(context);
	}

}
