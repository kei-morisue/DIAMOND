package diamond.paint.deletevertex;

import java.util.Collection;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class DeletingVertex extends PickingVertex {

	@Override
	protected void initialize() {

	}

	@Override
	protected void onResult(PaintContext context) {
		Doc document = DocHolder.getDoc();
		Collection<OriLine> creasePattern = document.getCreasePattern();

		if(context.getVertexCount() > 0){
			document.pushUndoInfo();

			Painter painter = new Painter();
			painter.removeVertex(context.popVertex(), creasePattern);

		}
		
		context.clear(false);
	}

}
