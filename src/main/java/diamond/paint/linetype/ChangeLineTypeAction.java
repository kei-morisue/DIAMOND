package diamond.paint.linetype;

import java.awt.Graphics2D;
import java.util.Collection;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.EditMode;
import diamond.paint.core.PaintContext;
import diamond.paint.core.RectangularSelectableAction;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;
import diamond.viewsetting.main.uipanel.UIPanelSettingDB;

public class ChangeLineTypeAction extends RectangularSelectableAction {


	public ChangeLineTypeAction(){
		setEditMode(EditMode.CHANGE_TYPE);
		setActionState(new SelectingLineForLineType());
	}


	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {

		super.onDraw(g2d, context);

		drawPickCandidateLine(g2d, context);
	}


	@Override
	protected void afterRectangularSelection(Collection<OriLine> selectedLines,
			PaintContext context) {

		if(selectedLines.isEmpty() == false){
			Doc document = DocHolder.getInstance().getDoc();
			Collection<OriLine> creasePattern = document.getCreasePattern();
			document.pushUndoInfo();

			UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
			for (OriLine l : selectedLines) {
				Painter painter = new Painter();
				// Change line type
				painter.alterLineType(
						l, setting.getTypeFrom(), setting.getTypeTo(),
						creasePattern);
				//ORIPA.doc.alterLineType(l, setting.getLineTypeFromIndex(), setting.getLineTypeToIndex());
			}

		}
	}



}
