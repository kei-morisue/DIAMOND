package diamond.appstate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A template for grouped state.
 * @author koji
 *
 * @param <GroupEnum> Enum of group identifier
 */
public class ApplicationState<GroupEnum> implements GroupMember<GroupEnum> {
	private ArrayList<ActionListener> actions = new ArrayList<ActionListener>();
	// TODO: use a class different from ActionListener which performs with no parameters.
	
	private final GroupEnum group;
	
	/**
	 * A constructor which binds a group and actions.
	 * @param group		group identifier
	 * @param actions	actions to be performed on this state.
	 */
	public ApplicationState(GroupEnum group, ActionListener[] actions) {
		this.group = group;
		addActions(actions);
	}
	
	public void addAction(ActionListener action){
		this.actions.add(action);		
	}

	public void addActions(ActionListener[] actions){
		if(actions == null){
			return;
		}
		
		for(ActionListener action : actions){
			addAction(action);
		}
		
	}
	
	@Override
	public GroupEnum getGroup() {
		return group;
	}

	/**
	 * performs actions of this state.
	 * 
	 * @param e
	 */
	public void performActions(ActionEvent e){
		if(actions == null){
			return;
		}
		
		for(ActionListener action : actions){
			action.actionPerformed(e);
		}
	}
	
}
