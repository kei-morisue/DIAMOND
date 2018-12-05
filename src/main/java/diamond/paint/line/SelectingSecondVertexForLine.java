package diamond.paint.line;

import javax.vecmath.Vector2d;

import diamond.doc.DocHolder;
import diamond.geom.GeomUtil;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.resource.Constants;
import diamond.value.OriLine;

public class SelectingSecondVertexForLine extends PickingVertex{


	public SelectingSecondVertexForLine(){
		super();
	}

	@Override
	protected void initialize() {
		setPreviousClass(SelectingFirstVertexForLine.class);
		setNextClass(SelectingFirstVertexForLine.class);

		//System.out.println("SelectingSecondVertex.initialize() is called");
	}

	@Override
	protected void onResult(PaintContext context) {

		if(context.getVertexCount() != 2){
			throw new RuntimeException();
		}

		Vector2d p0, p1;
		p0 = context.getVertex(0);
		p1 = context.getVertex(1);

		Vector2d dir = new Vector2d(p0.x - p1.x, p0.y - p1.y);
		dir.normalize();
		dir.scale(Constants.DEFAULT_PAPER_SIZE * 8);

		// create new line
		OriLine line = new OriLine(p0.x - dir.x, p0.y - dir.y,
				p0.x + dir.x, p0.y + dir.y, PaintConfig.inputLineType);

		double paperSize = DocHolder.getDoc().getPaperSize();

		// add new line to crease pattern
		if (GeomUtil.clipLine(line, paperSize / 2)) {
			DocHolder.getDoc().pushUndoInfo();
			DocHolder.getDoc().addLine(line);
		}

		context.clear(false);
	}
}	
