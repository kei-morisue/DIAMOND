package diamond.paint.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.ScreenUpdaterInterface;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;
import diamond.viewsetting.paint.ScreenUpdater;

public class DeleteSelectedLines implements ActionListener {

	PaintContext context = PaintContext.getInstance();
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Doc document = DocHolder.getDoc();
		Collection<OriLine> creasePattern = document.getCreasePattern();

		document.pushUndoInfo();

		Painter painter = new Painter();
		painter.removeSelectedLines(creasePattern);

		if(context.isPasting() == false){
			context.clear(false);
		}
		
		ScreenUpdaterInterface screenUpdater = ScreenUpdater.getInstance();
		
		screenUpdater.updateScreen();

	}

}
