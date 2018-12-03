package diamond.bind.copypaste;

import java.awt.geom.AffineTransform;
import java.util.Collection;

import diamond.appstate.ApplicationState;
import diamond.appstate.StateManager;
import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.EditMode;
import diamond.paint.copypaste.CopyAndPasteAction;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.Painter;
import diamond.value.OriLine;

public class CopyAndPasteActionWrapper extends CopyAndPasteAction {


    private boolean isCut;

    public CopyAndPasteActionWrapper(boolean isCut) {
        super();
        this.isCut = isCut;
        if (isCut) {
            super.setEditMode(EditMode.CUT);
        }
    }

    @Override
    public void onRightClick(PaintContext context, AffineTransform affine,
            boolean differentAction) {

        StateManager stateManager = StateManager.getInstance();
        ApplicationState<EditMode> prev = stateManager.pop();

        if (prev == null) {
            return;
        }

        // a case having switched copy to cut.
        prev.performActions(null);
    }

    @Override
    public void recover(PaintContext context) {
        super.recover(context);
        Doc document = DocHolder.getInstance().getDoc();
        Collection<OriLine> creasePattern = document.getCreasePattern();
        if (isCut) {
            Painter painter = new Painter();
            painter.removeSelectedLines(creasePattern);
        }
    }

}
