package diamond.view.resource.string.label;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.LABEL;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { LABEL.TITLE.name(),
                    "DIAMOND : Origami Diagram Editor" },
            { LABEL.AXIOM1.name(), "Origami Axiom 1" },
    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
