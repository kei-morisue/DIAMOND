package diamond.viewsetting.paint.uipanel;

import diamond.viewsetting.ChangeViewSetting;

public class ChangeOnSelectButtonSelected implements ChangeViewSetting {

	@Override
	public void changeViewSetting() {
		UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
		
		setting.selectSelectMode();
		
		setting.setValuePanelVisible(false);
		
		setting.setAlterLineTypePanelVisible(false);

		setting.setMountainButtonEnabled(false);
		setting.setValleyButtonEnabled(false);
		setting.setAuxButtonEnabled(false);

		setting.notifyObservers();

	}

}
