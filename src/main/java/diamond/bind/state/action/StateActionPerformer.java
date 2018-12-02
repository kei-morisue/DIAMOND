package diamond.bind.state.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.appstate.ApplicationState;
import diamond.paint.EditMode;


public class StateActionPerformer implements ActionListener{
	private ApplicationState<EditMode> state;
	
	public StateActionPerformer(ApplicationState<EditMode> s) {
		state = s;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		state.performActions(e);
	}
}