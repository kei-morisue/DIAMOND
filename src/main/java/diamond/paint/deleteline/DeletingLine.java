package diamond.paint.deleteline;

import java.util.Collection;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingLine;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class DeletingLine extends PickingLine {

	
	
	public DeletingLine() {
		super();
	}

	@Override
	protected void initialize() {
	}
	

	@Override
	protected void onResult(PaintContext context) {

		Doc document = DocHolder.getDoc();
		Collection<OriLine> creasePattern = document.getCreasePattern();

		if(context.getLineCount() > 0){
			document.pushUndoInfo();

			Painter painter = new Painter();
			painter.removeLine(context.popLine(), creasePattern);
		}
		
		context.clear(false);
	}

}
