package diamond.view.resource.string.hint;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.HINT;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { HINT.AXIOM1.name(),
                    "Specify two points that lie on the line" },
    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
