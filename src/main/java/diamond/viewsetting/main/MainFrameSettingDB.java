package diamond.viewsetting.main;

import diamond.viewsetting.ViewSettingDataBase;

public class MainFrameSettingDB extends ViewSettingDataBase{

	private static MainFrameSettingDB instance = null;
	
	
	
	
	public static MainFrameSettingDB getInstance(){
		if(instance == null){
			instance = new MainFrameSettingDB();
		}
		
		return instance;
	}

	private String hint;
	
	private MainFrameSettingDB(){}
	
	
	public String getHint() {
		return hint;
	}




	@Override
	public String getName() {
		return this.getClass().getName();
	}




	public void setHint(String hint) {
		this.hint = hint;
		this.setChanged();
	}
}
