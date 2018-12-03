package diamond.appstate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import diamond.paint.EditMode;

public class StatePopper implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		ApplicationState<EditMode> current = pop();

		if(current == null){
			return;
		}
		
		current.performActions(e);
	}
	
	public ApplicationState<EditMode> pop(){
		StateManager manager = StateManager.getInstance();
		return manager.pop();
	}
}
