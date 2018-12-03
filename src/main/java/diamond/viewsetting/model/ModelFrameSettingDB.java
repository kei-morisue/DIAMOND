package diamond.viewsetting.model;

import diamond.viewsetting.ViewSettingDataBase;

public class ModelFrameSettingDB extends ViewSettingDataBase{

	private static ModelFrameSettingDB instance = null;
	
	
	
	
	public static ModelFrameSettingDB getInstance(){
		if(instance == null){
			instance = new ModelFrameSettingDB();
		}
		
		return instance;
	}

	private boolean frameVisible;
	
	private ModelFrameSettingDB(){}
	
	




	@Override
	public String getName() {
		return this.getClass().getName();
	}

	public boolean isFrameVisible() {
		return frameVisible;
	}

	public void setFrameVisible(boolean frameVisible) {
		this.frameVisible = frameVisible;
		this.setChanged();
	}
}
