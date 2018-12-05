package diamond.paint.bisector;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingLine;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;

public class SelectingLineForBisector extends PickingLine {

	@Override
	protected void initialize() {
		setPreviousClass(SelectingVertexForBisector.class);
		setNextClass(SelectingVertexForBisector.class);

	}

	
	
	@Override
	protected void onResult(PaintContext context) {
		if(context.getLineCount() != 1 || 
				context.getVertexCount() != 3){
			throw new RuntimeException();
		}
		
		Doc document = DocHolder.getDoc();
		CreasePattern creasePattern = document.getCreasePattern();
		document.pushCachedUndoInfo();

		Painter painter = new Painter();
		
		painter.addBisectorLine(
        		context.getVertex(0), context.getVertex(1), context.getVertex(2), 
        		context.getLine(0),
        		creasePattern);

        context.clear(false);
	}

	

	@Override
	protected void undoAction(PaintContext context) {
		context.popVertex();
	
	}

}
