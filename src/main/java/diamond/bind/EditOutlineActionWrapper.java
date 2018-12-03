package diamond.bind;

import java.awt.geom.AffineTransform;

import diamond.appstate.ApplicationState;
import diamond.appstate.StateManager;
import diamond.paint.EditMode;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.outline.EditOutlineAction;

public class EditOutlineActionWrapper extends EditOutlineAction {
	
	
	
	
	
	
	private void popPreviousState(){
		StateManager stateManager = StateManager.getInstance();
		ApplicationState<EditMode> prev = stateManager.pop();
		
		prev.performActions(null);

	}

	@Override
	public GraphicMouseActionInterface onLeftClick(PaintContext context,
			AffineTransform affine, boolean differentAction) {
		GraphicMouseActionInterface next = super.onLeftClick(context, affine, differentAction);
		
		if(context.isMissionCompleted()){
			popPreviousState();
			next = PaintConfig.getMouseAction();
		}
		
		return next;
	}

	@Override
	public void onRightClick(PaintContext context, AffineTransform affine,
			boolean differentAction) {

		popPreviousState();
	}
	
}
