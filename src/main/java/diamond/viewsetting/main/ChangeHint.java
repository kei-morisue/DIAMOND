package diamond.viewsetting.main;

import java.util.ResourceBundle;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.viewsetting.ChangeViewSetting;

public class ChangeHint implements ChangeViewSetting {

	private MainFrameSettingDB frameSetting = MainFrameSettingDB.getInstance();

	private String id;
	
	public ChangeHint(String resourceID){
		this.id = resourceID;
	}
	
	@Override
	public void changeViewSetting() {
		ResourceHolder holder = ResourceHolder.getInstance();
		
		ResourceBundle resource = holder.getResource(ResourceKey.EXPLANATION);

		String hint = null;
		try{
			hint = resource.getString(id);
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
		frameSetting.setHint(hint);
		
		frameSetting.notifyObservers();
	}

}
