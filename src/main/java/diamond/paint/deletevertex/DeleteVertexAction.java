package diamond.paint.deletevertex;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import diamond.paint.EditMode;
import diamond.paint.core.GraphicMouseAction;
import diamond.paint.core.PaintContext;

public class DeleteVertexAction extends GraphicMouseAction {


	public DeleteVertexAction(){
		setEditMode(EditMode.VERTEX);

		setActionState(new DeletingVertex());

	}


	@Override
	public void onDrag(PaintContext context, AffineTransform affine,
			boolean differentAction) {

	}


	@Override
	public void onDraw(Graphics2D g2d, PaintContext context) {

		super.onDraw(g2d, context);

		drawPickCandidateVertex(g2d, context);
	}

	@Override
	public void onPress(PaintContext context, AffineTransform affine,
			boolean differentAction) {
	}

	@Override
	public void onRelease(PaintContext context, AffineTransform affine, boolean differentAction) {


	}



}
