package diamond.paint.core;

import java.awt.geom.Point2D;

public interface ActionState {
	public ActionState doAction(PaintContext context, 
			Point2D.Double currentPoint, boolean differentAction);

	public ActionState getNextState();
	

	public ActionState getPreviousState();	
	public void setNextState(ActionState state);

	public void setPreviousState(ActionState state);	
	public ActionState undo(PaintContext context);

}
