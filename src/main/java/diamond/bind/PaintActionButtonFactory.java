package diamond.bind;

import java.awt.Component;

import javax.swing.AbstractButton;

import diamond.appstate.ApplicationState;
import diamond.bind.binder.ApplicationStateButtonBinder;
import diamond.bind.state.PaintBoundStateFactory;
import diamond.paint.EditMode;
import diamond.paint.core.PaintContext;


/**
 * A class for application-specific binding of state actions and buttons.
 * @author koji
 *
 */
public class PaintActionButtonFactory implements ButtonFactory {

	PaintContext context = PaintContext.getInstance();

	/* (non-Javadoc)
	 * @see oripa.bind.ButtonFactory#create(java.awt.Component, java.lang.Class, java.lang.String)
	 */
	@Override
	public AbstractButton create(Component parent,
			Class<? extends AbstractButton> buttonClass, String id){

		PaintBoundStateFactory stateFactory = 
				new PaintBoundStateFactory();


		ApplicationState<EditMode> state = stateFactory.create(parent, id);


		if(state == null){
			throw new NullPointerException("Wrong ID for creating state");
		}

		ApplicationStateButtonBinder paintBinder = 
				new  ApplicationStateButtonBinder();
		AbstractButton button = paintBinder.createButton(buttonClass, state, id);

		return button;
	}

}
