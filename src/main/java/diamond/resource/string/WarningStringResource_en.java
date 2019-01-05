package diamond.resource.string;

import java.util.ListResourceBundle;

import diamond.resource.string.StringKey.WARNING;

public class WarningStringResource_en extends ListResourceBundle {

    static final String[][] strings = {
            { WARNING.SAME_FILE_EXISTS.name(),
                    "Same name file exists. Over write?" },
            { WARNING.FOLD_FAILED_DUPLICATION.name(),
                    "Failed to fold. Try again by deleting duplicating segments?" },
            { WARNING.FOLD_FAILED_WRONG_STRUCTURE.name(),
                    "Failed to fold. It seems the pattern has basic problems." },
            { WARNING.SAVE_FAILED.name(), "Failed to save." },
            { WARNING.LOAD_FAILED.name(), "Failed to load." },

    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
