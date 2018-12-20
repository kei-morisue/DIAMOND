package diamond.viewsetting.paint;

import diamond.viewsetting.ViewSettingDataBase;

public class PaintScreenSettingDB extends ViewSettingDataBase{

	
	// ---------
	private static PaintScreenSettingDB instance = null;


	public static PaintScreenSettingDB getInstance(){
		if(instance == null){
			instance = new PaintScreenSettingDB();
		}
		
		return instance;
	}

	private boolean gridVisible = true;
	
	private PaintScreenSettingDB(){}

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
