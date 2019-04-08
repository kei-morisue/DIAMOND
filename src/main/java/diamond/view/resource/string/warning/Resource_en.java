package diamond.view.resource.string.warning;

import java.util.ListResourceBundle;

import diamond.model.geom.Constants;
import diamond.view.resource.string.StringKey.WARNING;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { WARNING.SAME_FILE_EXISTS.name(),
                    "Same name file exists. Over write?" },
            { WARNING.SAVE_FAILED.name(), "Failed to save." },
            { WARNING.LOAD_FAILED.name(), "Failed to load." },
            { WARNING.WRONG_FACE.name(), "Too much vertices( >"
                    + (Constants.MAX_POLYGON) + ") on a OriFace" },
            { WARNING.DESTROY.name(),
                    "Are you sure to destroy current diagram?" },
    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
