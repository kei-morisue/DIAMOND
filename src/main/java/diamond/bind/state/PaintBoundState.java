package diamond.bind.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.appstate.ApplicationState;
import diamond.appstate.StatePusher;
import diamond.bind.state.action.PaintActionSetter;
import diamond.paint.EditMode;
import diamond.paint.GraphicMouseActionInterface;

public class PaintBoundState extends ApplicationState<EditMode> {

    private ErrorListener errorListener;

    public PaintBoundState(ErrorListener el,
            GraphicMouseActionInterface mouseAction,
            ActionListener[] actions) {

        super(mouseAction.getEditMode(), actions);
        errorListener = el;
        addBasicListeners(mouseAction);
    }

    private void addBasicListeners(GraphicMouseActionInterface mouseAction) {

        // add a listener to push this state to the history stack.
        addAction(new StatePusher(this));

        // add a listener to change paint action.
        addAction(new PaintActionSetter(mouseAction));

    }

    @Override
    public void performActions(ActionEvent e) {
        if (errorListener != null) {
            if (errorListener.isError(e)) {
                errorListener.onError(e);
                return;
            }
        }
        super.performActions(e);
    }

}
