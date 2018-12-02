package diamond.paint.copypaste;

import java.awt.geom.Point2D.Double;

import javax.vecmath.Vector2d;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingVertex;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.paint.geometry.GeometricOperation;

public class PastingOnVertex extends PickingVertex {

	private FilledOriLineArrayList shiftedLines;

	@Override
	protected void initialize() {
	}

	
	
	@Override
	protected void undoAction(PaintContext context) {
		context.setMissionCompleted(false);
		DocHolder.getInstance().getDoc().loadUndoInfo();
	}


	
	

	@Override
	protected boolean onAct(PaintContext context, Double currentPoint,
			boolean freeSelection) {
		if(context.pickCandidateV == null){
			return false;
		}
		
		context.pushVertex(context.pickCandidateV);
		
		return true;
	}



	@Override
	protected void onResult(PaintContext context) {

        Vector2d v = context.popVertex();
        
        if (context.getLineCount() > 0) {
        	Doc document = DocHolder.getInstance().getDoc();
        	CreasePattern creasePattern = document.getCreasePattern();
        	document.pushUndoInfo();

        	Vector2d origin = OriginHolder.getInstance().getOrigin(context);

        	double ox = origin.x;
            double oy = origin.y;


            shiftedLines = new FilledOriLineArrayList(context.getLineCount());
        	GeometricOperation.shiftLines(context.getLines(), shiftedLines, v.x - ox, v.y -oy);
            
//            for(int i = 0; i < context.getLineCount(); i++){
//            	ORIPA.doc.addLine(shiftedLines.get(i));
//            }

        	Painter painter = new Painter();
        	painter.pasteLines(shiftedLines, creasePattern);
            
            context.setMissionCompleted(true);
        }
		
	}

}
