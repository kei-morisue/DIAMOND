package diamond.viewsetting.main;

import diamond.resource.ResourceHolder;
import diamond.viewsetting.ChangeViewSetting;

public class ChangeHint implements ChangeViewSetting {

    private String id;

    public ChangeHint(String resourceID) {
        this.id = resourceID;
    }

    @Override
    public void changeViewSetting() {
        String hint = null;
        try {
            hint = ResourceHolder.getHintString(id);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        MainFrameSettingDB.getInstance().setHint(hint);

        MainFrameSettingDB.getInstance().notifyObservers();
    }

}
