package diamond.paint.vertical;

import diamond.doc.DocHolder;
import diamond.geom.GeomUtil;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingLine;
import diamond.value.OriLine;

public class SelectingLineForVertical extends PickingLine {

	@Override
	protected void initialize() {
		setPreviousClass(SelectingVertexForVertical.class);
		setNextClass(SelectingVertexForVertical.class);

	}

	
	
	@Override
	protected void undoAction(PaintContext context) {
		context.clear(false);
	
	}

	

	@Override
	protected void onResult(PaintContext context) {
		if(context.getLineCount() != 1 || 
				context.getVertexCount() != 1){
			throw new RuntimeException();
		}
		
        OriLine vl = GeomUtil.getVerticalLine(
        		context.getVertex(0), context.getLine(0), PaintConfig.inputLineType);

        DocHolder.getInstance().getDoc().pushUndoInfo();
        DocHolder.getInstance().getDoc().addLine(vl);

        context.clear(false);
	}

}
