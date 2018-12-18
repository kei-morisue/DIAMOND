package diamond.bind.button;

import java.awt.event.ActionListener;
import java.util.MissingResourceException;

import javax.swing.AbstractButton;

import diamond.appstate.ApplicationState;
import diamond.bind.state.ErrorListener;
import diamond.bind.state.PaintBoundState;
import diamond.bind.state.action.StateActionPerformer;
import diamond.paint.EditMode;
import diamond.paint.GraphicMouseActionInterface;
import diamond.resource.ResourceHolder;

public abstract class PaintActionButtonFactory {

    protected abstract String getLabelKey();

    protected abstract java.awt.event.KeyListener buildKeyListner();

    protected abstract ErrorListener buildErrorListener();

    protected abstract GraphicMouseActionInterface buildMouseAction();

    protected abstract ActionListener[] buildViewChangeListeners();

    private ApplicationState<EditMode> buildPaintBoundState() {
        return new PaintBoundState(
                buildErrorListener(),
                buildMouseAction(),
                buildViewChangeListeners());
    }

    protected ActionListener buildActionListner() {
        return new StateActionPerformer(buildPaintBoundState());

    };

    protected String buildLabel() {
        try {
            return ResourceHolder.getLabelString(getLabelKey());
        } catch (MissingResourceException e) {
            return "";
        }
    };

    private <T extends AbstractButton> T createEmptyButton(
            Class<T> buttonClass) {
        T button = null;
        try {
            button = buttonClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return button;
    }

    public <T extends AbstractButton> T create(Class<T> buttonClass) {
        T button = createEmptyButton(buttonClass);
        button.addActionListener(buildActionListner());
        button.setText(buildLabel());
        button.addKeyListener(buildKeyListner());
        return button;
    }
}
