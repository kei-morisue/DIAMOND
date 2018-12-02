package diamond.viewsetting.main.uipanel;

import diamond.viewsetting.ChangeViewSetting;

public class ChangeOnAlterTypeButtonSelected implements ChangeViewSetting{
	@Override
	public void changeViewSetting() {
		UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
		
		setting.setValuePanelVisible(false);
		
		setting.setAlterLineTypePanelVisible(true);

		setting.setMountainButtonEnabled(false);
		setting.setValleyButtonEnabled(false);
		setting.setAuxButtonEnabled(false);

		setting.notifyObservers();

	}

}
