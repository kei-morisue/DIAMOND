package diamond.viewsetting.estimation;

import diamond.viewsetting.ViewSettingDataBase;

public class RenderFrameSettingDB extends ViewSettingDataBase{

	private static RenderFrameSettingDB instance = null;
	
	
	
	
	public static RenderFrameSettingDB getInstance(){
		if(instance == null){
			instance = new RenderFrameSettingDB();
		}
		
		return instance;
	}

	private boolean frameVisible;
	
	private RenderFrameSettingDB(){}
	
	




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
