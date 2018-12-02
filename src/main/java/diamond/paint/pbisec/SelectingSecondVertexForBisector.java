package diamond.paint.pbisec;

import javax.vecmath.Vector2d;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;

public class SelectingSecondVertexForBisector extends PickingVertex{
	
	public SelectingSecondVertexForBisector(){
		super();
	}
	
	@Override
	public void onResult(PaintContext context) {
		
		if(context.getVertexCount() != 2){
			throw new RuntimeException();
		}
		
		Vector2d p0, p1;
		p0 = context.getVertex(0);
		p1 = context.getVertex(1);
		
		Doc document = DocHolder.getInstance().getDoc();
		CreasePattern creasePattern = document.getCreasePattern();

        document.pushUndoInfo();

        Painter painter = new Painter();
        painter.addPBisector(
        		p0, p1,
        		creasePattern, creasePattern.getPaperSize());

        context.clear(false);
	}

	@Override
	protected void initialize() {
		setPreviousClass(SelectingFirstVertexForBisector.class);
		setNextClass(SelectingFirstVertexForBisector.class);
		
//		System.out.println("SelectingFirstVertex.initialize() is called");
	}
	
}
