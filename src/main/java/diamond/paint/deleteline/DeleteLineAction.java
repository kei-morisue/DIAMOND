package diamond.paint.deleteline;

import java.awt.Graphics2D;
import java.util.Collection;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.EditMode;
import diamond.paint.core.GraphicMouseAction;
import diamond.paint.core.PaintContext;
import diamond.paint.core.RectangularSelectableAction;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class DeleteLineAction extends RectangularSelectableAction {


	public DeleteLineAction(){
		setEditMode(EditMode.OTHER);

		setActionState(new DeletingLine());

	}

	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {

		super.onDraw(g2d, context);

		drawPickCandidateLine(g2d, context);
		
	}

	/**
	 * Reset selection mark to avoid undesired deletion.
	 * @see GraphicMouseAction#recover(PaintContext)
	 * @param context
	 */
	@Override
	public void recover(PaintContext context) {
		context.clear(true);
	}
	
	@Override
	protected void afterRectangularSelection(Collection<OriLine> selectedLines,
			PaintContext context) {

		Doc document = DocHolder.getInstance().getDoc();
		Collection<OriLine> creasePattern = document.getCreasePattern();

		if(selectedLines.isEmpty() == false){
			document.pushUndoInfo();
			Painter painter = new Painter();
			for (OriLine l : selectedLines) {
				painter.removeLine(l, creasePattern);
			}
		}		
	}



}
