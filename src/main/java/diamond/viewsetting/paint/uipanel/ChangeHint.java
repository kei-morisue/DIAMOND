package diamond.viewsetting.paint.uipanel;

import diamond.viewsetting.ChangeViewSetting;
import diamond.viewsetting.paint.MainFrameSettingDB;

public class ChangeHint implements ChangeViewSetting {

    private String hint;

    public ChangeHint(String hint) {
        this.hint = hint;
    }

    @Override
    public void changeViewSetting() {
        MainFrameSettingDB.getInstance().setHint(hint);
        MainFrameSettingDB.getInstance().notifyObservers();
    }

}
