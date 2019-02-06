package diamond.view.resource.string.label;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.LABEL;

public class Resource_ja extends ListResourceBundle {

    static final String[][] strings = {
            { LABEL.TITLE.name(),
                    "DIAMOND : ダイヤグラム製図専用エディタ" },
            { LABEL.AXIOM1.name(),
                    "折紙公理1" },
    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
