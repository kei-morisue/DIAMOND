package diamond.view.resource.string.label;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.LABEL;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { LABEL.TITLE.name(),
                    "DIAMOND : Origami Diagram Editor" },

            { LABEL.INPUT_LINE.name(), "Input Line" },
            { LABEL.LINE_PATTERN.name(), "Line Pattern" },
            { LABEL.MODIFY_LINE_TYPE.name(), "Modify Line Type" },

            { LABEL.AXIOM1.name(), "Origami Axiom 1" },
            { LABEL.AXIOM2.name(), "Origami Axiom 2" },
            { LABEL.AXIOM3.name(), "Origami Axiom 3" },
            { LABEL.AXIOM4.name(), "Origami Axiom 4" },
            { LABEL.MOUNTAIN.name(), "Mountain" },
            { LABEL.VALLEY.name(), "Valley" },
            { LABEL.AUX.name(), "Crease" },
            { LABEL.AUX_VALLEY.name(), "Valley" },
            { LABEL.AUX_MOUNTAIN.name(), "Mountain" },

            { LABEL.CUT.name(), "Cut" },

            { LABEL.FLIP_LINE_TYPE.name(), "Flip Line Type(M <-> V)" },
            { LABEL.UNFOLD.name(), "Fold / Unfold Line" },
            { LABEL.SETTLE_UNSETTLE.name(), "Settle/ Unsettle Line Type" },
            { LABEL.DELETE_LINE.name(), "Delete Line" },
            { LABEL.SYMMETRIC.name(), "Mirroring the Line" },

            { LABEL.MODEL_EDIT.name(), "Edit Model" },
            { LABEL.BASE_FACE.name(), "Base Face" },
            { LABEL.FACE_TOP.name(), "Set the face top" },
            { LABEL.FACE_BOTTOM.name(), "Set the face botom" },

            { LABEL.FILE.name(), "File" },
            { LABEL.SAVE.name(), "Save" },
            { LABEL.OPEN.name(), "Open" },
    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
