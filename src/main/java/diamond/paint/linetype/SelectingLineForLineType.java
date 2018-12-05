package diamond.paint.linetype;

import java.util.Collection;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.core.PickingLine;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;
import diamond.viewsetting.main.uipanel.UIPanelSettingDB;

public class SelectingLineForLineType extends PickingLine {

	
	
	public SelectingLineForLineType() {
		super();
	}

	@Override
	protected void initialize() {
	}
	
	
	@Override
	protected void onResult(PaintContext context) {
		Doc document = DocHolder.getDoc();
		Collection<OriLine> creasePattern = document.getCreasePattern();

		document.pushUndoInfo();

    	UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
    	Painter painter = new Painter();
    	painter.alterLineType(
    			context.peekLine(),  setting.getTypeFrom(), setting.getTypeTo(),
    			creasePattern);

        context.clear(false);
	}

	@Override
	protected void undoAction(PaintContext context) {
		super.undoAction(context);
	}

}
