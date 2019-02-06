package diamond.view.resource.string.warning;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.WARNING;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { WARNING.SAME_FILE_EXISTS.name(),
                    "Same name file exists. Over write?" },
            { WARNING.SAVE_FAILED.name(), "Failed to save." },
            { WARNING.LOAD_FAILED.name(), "Failed to load." },

    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
