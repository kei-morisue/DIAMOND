package diamond.viewsetting.paint.uipanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import diamond.paint.creasepattern.tool.TypeForChange;

public class ToLineTypeItemListener implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		UIPanelSettingDB settingDB = UIPanelSettingDB.getInstance();
		
		if(e.getStateChange() == ItemEvent.SELECTED){
			settingDB.setTypeTo(
					(TypeForChange)e.getItem());	

		}
	}
}