package diamond.viewsetting;

import java.util.Observable;

public abstract class ViewSettingDataBase extends Observable {

    public String getName() {
        return this.getClass().getName();
    }

    /**
     * A tool to be identified by observers.
     * @param name
     * @return
     */
    public boolean hasGivenName(String name) {
        return getName().equals(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
