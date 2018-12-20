package diamond.bind.state.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.doc.DocHolder;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.ScreenUpdaterInterface;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.viewsetting.paint.ScreenUpdater;


/**
 * Add this listener to Button object or something for selecting paint action.
 *
 * @author koji
 *
 */
public class PaintActionSetter implements ActionListener{

    private GraphicMouseActionInterface mouseAction;

    public PaintActionSetter(GraphicMouseActionInterface mouseAction) {
        this.mouseAction = mouseAction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintContext context = PaintContext.getInstance();

        PaintConfig.getMouseAction().destroy(context);
        mouseAction.recover(context);

        PaintConfig.setMouseAction(mouseAction);

        if (mouseAction.needSelect() == false) {
            CreasePattern creasePattern = DocHolder.getDoc()
                    .getCreasePattern();
            Painter painter = new Painter();
            painter.resetSelectedOriLines(creasePattern);
        }

        ScreenUpdaterInterface screenUpdater = ScreenUpdater.getInstance();
        screenUpdater.updateScreen();
    }


}

