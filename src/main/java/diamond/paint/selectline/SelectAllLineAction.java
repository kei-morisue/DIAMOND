package diamond.paint.selectline;

import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;

public class SelectAllLineAction extends SelectLineAction {

	public SelectAllLineAction(PaintContext context) {
		super(context);
	}

	@Override
	public void recover(PaintContext context) {
		Painter painter = new Painter();
		CreasePattern creasePattern = DocHolder.getInstance().getDoc().getCreasePattern();
		painter.selectAllOriLines(creasePattern);
		super.recover(context);
	}
	


}
