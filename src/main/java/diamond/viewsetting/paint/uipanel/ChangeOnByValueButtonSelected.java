package diamond.viewsetting.paint.uipanel;

import diamond.viewsetting.ChangeViewSetting;

public class ChangeOnByValueButtonSelected implements ChangeViewSetting {

	@Override
	public void changeViewSetting() {
		UIPanelSettingDB setting = UIPanelSettingDB.getInstance();
		
		setting.selectInputMode();
		
		setting.setValuePanelVisible(true);
		setting.setAlterLineTypePanelVisible(false);
		setting.setMountainButtonEnabled(true);
		setting.setValleyButtonEnabled(true);
		setting.setAuxButtonEnabled(true);

		setting.notifyObservers();

	}

}
