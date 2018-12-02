package diamond.paint.selectline;

import java.awt.Graphics2D;
import java.util.Collection;

import diamond.doc.DocHolder;
import diamond.paint.EditMode;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.core.RectangularSelectableAction;
import diamond.value.OriLine;

public class SelectLineAction extends RectangularSelectableAction {


	public SelectLineAction(PaintContext context){
		setEditMode(EditMode.SELECT);
		setNeedSelect(true);
		
		setActionState(new SelectingLine());


		recover(context);
	}

	/**
	 * set old line-selected marks to current context.s
	 */
	@Override
	public void undo(PaintContext context) {
		DocHolder.getInstance().getDoc().loadUndoInfo();

		recover(context);
	}


	@Override
	public void recover(PaintContext context) {
		context.clear(false);

		Collection<OriLine> creasePattern = DocHolder.getInstance().getDoc().getCreasePattern();
		if(creasePattern == null){
			return;
		}
		
		for(OriLine line : creasePattern){
			if(line.selected){
				context.pushLine(line);
			}
		}
	}


	@Override
	protected void afterRectangularSelection(Collection<OriLine> selectedLines,
			PaintContext context) {

		if(selectedLines.isEmpty() == false){

			DocHolder.getInstance().getDoc().pushUndoInfo();

			for(OriLine line : selectedLines){
				if (line.typeVal == OriLine.TYPE_CUT) {
					continue;
				}
				// Don't select if the line is hidden
				if (!PaintConfig.dispMVLines && (line.typeVal == OriLine.TYPE_RIDGE
						|| line.typeVal == OriLine.TYPE_VALLEY)) {
					continue;
				}
				if (!PaintConfig.dispAuxLines && line.typeVal == OriLine.TYPE_NONE) {
					continue;
				}

				if(context.getLines().contains(line) == false){
					line.selected = true;
					context.pushLine(line);
				}

			}

		}
	}



	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {
		super.onDraw(g2d, context);

		this.drawPickCandidateLine(g2d, context);
	}





}
