package diamond.viewsetting.main;

import diamond.viewsetting.ViewSettingDataBase;

public class MainScreenSettingDB extends ViewSettingDataBase{

	
	// ---------
	private static MainScreenSettingDB instance = null;


	public static MainScreenSettingDB getInstance(){
		if(instance == null){
			instance = new MainScreenSettingDB();
		}
		
		return instance;
	}

	private boolean gridVisible = true;
	
	private MainScreenSettingDB(){}

//	public static final String REDRAW_REQUESTED = "redraw requested";
//	public void requestRedraw(){
//		setChanged();
//		notifyObservers(REDRAW_REQUESTED);
//	}
	
	
	public boolean isGridVisible() {
		return gridVisible;
	}



	public void setGridVisible(boolean gridVisible) {
		this.gridVisible = gridVisible;
		this.setChanged();
	}


//	@Override
//	public String getName() {
//		return this.getClass().getName();
//	}
}
