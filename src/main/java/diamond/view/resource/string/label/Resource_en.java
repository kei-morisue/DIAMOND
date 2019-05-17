package diamond.view.resource.string.label;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.LABEL;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { LABEL.TITLE.name(),
                    "DIAMOND : Origami Diagram Editor" },

            { LABEL.CP.name(), "Edit Crease Pattern" },
            { LABEL.INPUT_LINE.name(), "Input Line" },
            { LABEL.LINE_PATTERN.name(), "Line Pattern" },
            { LABEL.MODIFY_LINE.name(), "Modify Line" },

            { LABEL.AXIOM1.name(), "Origami Axiom 1" },
            { LABEL.AXIOM2.name(), "Origami Axiom 2" },
            { LABEL.AXIOM3.name(), "Origami Axiom 3" },
            { LABEL.AXIOM4.name(), "Origami Axiom 4" },
            { LABEL.SYMMETRIC.name(), "Revursive Mirroring the Line" },
            { LABEL.MIRROR.name(), "Mirroring the Line" },

            { LABEL.MOUNTAIN.name(), "Mountain" },
            { LABEL.VALLEY.name(), "Valley" },
            { LABEL.CREASE.name(), "Crease" },
            { LABEL.UNSETTLED_VALLEY.name(), "Valley" },
            { LABEL.UNSETTLED_MOUNTAIN.name(), "Mountain" },
            { LABEL.CUT.name(), "Cut" },
            { LABEL.EDIT_VERTEX.name(), "Edit Vertex" },
            { LABEL.ADD_VERTEX.name(), "Add Vertex" },
            { LABEL.DELETE_VERTEX.name(), "Delete Vertex" },

            { LABEL.FLIP_LINE_TYPE.name(), "Flip Line Type(M <-> V)" },
            { LABEL.FOLD_UNFOLD.name(), "Fold / Unfold Line" },
            { LABEL.SETTLE_UNSETTLE.name(), "Settle / Unsettle Line Type" },
            { LABEL.DELETE_LINE.name(), "Delete Line" },
            { LABEL.CONTOUR.name(), "Modify the paper form" },

            { LABEL.FOLDED.name(), "Edit Folded Model" },
            { LABEL.FACE.name(), "Edit Face" },
            { LABEL.BASE_FACE.name(), "Base Face" },
            { LABEL.FACE_TOP.name(), "Set the face top" },
            { LABEL.FACE_BOTTOM.name(), "Set the face botom" },

            { LABEL.DIAGRAM_TAB.name(), "Diagram" },
            { LABEL.VALLEY_ARROW.name(), "Valley Fold" },
            { LABEL.MOUNTAIN_ARROW.name(), "Mountai Fold" },
            { LABEL.FOLD_UNFOLD_ARROW.name(), "Fold/Unfold" },
            { LABEL.FLIP_ARROW.name(), "Flip" },
            { LABEL.ROTATE_ARROW.name(), "rotate" },
            { LABEL.SINK_ARROW.name(), "Sink Fold" },
            { LABEL.PLEAT_ARROW.name(), "Pleat Fold" },
            { LABEL.SELECT_ARROW.name(), "Select Arrow" },
            { LABEL.SCALE_ARROW.name(), "Scale Arrow" },

            { LABEL.LANDMARK.name(), "Landmark" },

            { LABEL.FILE.name(), "File" },
            { LABEL.SAVE.name(), "Save" },
            { LABEL.OPEN.name(), "Open" },

            { LABEL.RUN.name(), "Run" },
            { LABEL.PREVIEW.name(), "Preview Pages" },

            { LABEL.OPTION.name(), "Option" },
            { LABEL.STYLE.name(), "Style" },
            { LABEL.LINE_TAB.name(), "Line" },
            { LABEL.CLIPPING_SCALE.name(), "Clipping scale for crease lines" },
            { LABEL.FACE_TAB.name(), "Face" },
            { LABEL.FACE_FRONT_COLOR.name(), "Face Front Color" },
            { LABEL.FACE_BACK_COLOR.name(), "Face Back Color" },

    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
