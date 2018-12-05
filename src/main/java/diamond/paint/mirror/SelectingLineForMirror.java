package diamond.paint.mirror;

import java.awt.geom.Point2D;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingLine;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class SelectingLineForMirror extends PickingLine {

	
	
	private OriLine axis;

	private boolean doingFirstAction = true;

	
	public SelectingLineForMirror() {
		super();
	}
	@Override
	protected void initialize() {
	}
	
	/**
	 * This class keeps selecting line while {@code doSpecial} is false.
	 * When {@value doSpecial} is true, it executes mirror copy where the
	 * axis of mirror copy is the selected line.
	 * 
	 * @param doSpecial true if copy should be done.
	 * @return true if copy is done.
	 */
	@Override
	protected boolean onAct(PaintContext context, Point2D.Double currentPoint,
			boolean doSpecial) {
		if(doingFirstAction){
			doingFirstAction = false;
			DocHolder.getDoc().cacheUndoInfo();
			
		}

		boolean result = super.onAct(context, currentPoint, doSpecial);
		
		if (result == true) {
			if (doSpecial) {
				axis = context.popLine();
				result = true;
            } 
			else {
				OriLine line = context.peekLine();

				if (line.selected) {
                	line.selected = false;
                	context.popLine();
                	context.removeLine(line);
                }
                else {
                	line.selected = true;
                }

                result = false;
            }
		}
		

		return result;
	}

	
	
	@Override
	protected void onResult(PaintContext context) {

		Doc document = DocHolder.getDoc();
		CreasePattern creasePattern = document.getCreasePattern();
		document.pushCachedUndoInfo();
		
        Painter painter = new Painter();
		painter.mirrorCopyBy(axis, context.getLines(), creasePattern);

        doingFirstAction = true;
        context.clear(true);
	}

	@Override
	protected void undoAction(PaintContext context) {
//		if (doingFirstAction) {
//			super.undoAction(context);
//			return;
//		}
		context.popLine();
	}

}
