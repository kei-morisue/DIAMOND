package diamond.appstate;

public interface StateManagerInterface<GroupEnum> {

	public ApplicationState<GroupEnum> getCurrent();
	public ApplicationState<GroupEnum> pop();
	public ApplicationState<GroupEnum> popLastOf(GroupEnum group);
	public void push(ApplicationState<GroupEnum> s);
}
