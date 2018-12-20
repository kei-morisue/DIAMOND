package diamond.viewsetting.paint.uipanel;

import java.util.MissingResourceException;

import diamond.resource.ResourceHolder;
import diamond.viewsetting.ChangeViewSetting;
import diamond.viewsetting.paint.MainFrameSettingDB;

public class ChangeHint implements ChangeViewSetting {

    private String hint;

    public ChangeHint(String resourceID) {
        try {
            hint = ResourceHolder.getHintString(resourceID);
        } catch (MissingResourceException e) {
            hint = "";
        }
    }

    @Override
    public void changeViewSetting() {
        MainFrameSettingDB.getInstance().setHint(hint);
        MainFrameSettingDB.getInstance().notifyObservers();
    }

}
