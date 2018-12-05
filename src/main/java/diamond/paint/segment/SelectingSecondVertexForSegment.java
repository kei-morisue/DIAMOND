package diamond.paint.segment;

import diamond.doc.DocHolder;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.value.OriLine;

public class SelectingSecondVertexForSegment extends PickingVertex{

		
	public SelectingSecondVertexForSegment(){
		super();
	}

	@Override
	protected void initialize() {
		setPreviousClass(SelectingFirstVertexForSegment.class);
		setNextClass(SelectingFirstVertexForSegment.class);

//		System.out.println("SelectingSecondVertex.initialize() is called");
	}

	@Override
	protected void onResult(PaintContext context) {
		
		if(context.getVertexCount() != 2){
			throw new RuntimeException();
		}
		
		OriLine line = new OriLine(context.getVertex(0),
				context.getVertex(1), PaintConfig.inputLineType);

		DocHolder.getDoc().pushUndoInfo();
        DocHolder.getDoc().addLine(line);

        context.clear(false);
	}
}	
