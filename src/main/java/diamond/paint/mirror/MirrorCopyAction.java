package diamond.paint.mirror;

import java.awt.geom.AffineTransform;

import diamond.paint.EditMode;
import diamond.paint.core.BasicUndo;
import diamond.paint.core.PaintContext;
import diamond.paint.selectline.SelectLineAction;

public class MirrorCopyAction extends SelectLineAction {

	
	public MirrorCopyAction(PaintContext context){
		super(context);

		setEditMode(EditMode.INPUT);
		setNeedSelect(true);
		
		setActionState(new SelectingLineForMirror());
	}

	
	
	@Override
	public void destroy(PaintContext context) {
		context.clear(false);
	}



	/**
	 * do usual undo.
	 */
	@Override
	public void onRightClick(PaintContext context, AffineTransform affine,
			boolean differentAction) {
		BasicUndo.undo(this.getActionState(), context);
	}

	
	

	
}
