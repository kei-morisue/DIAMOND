package diamond.view.resource.string;

public class StringKey {

    public enum LABEL {
        TITLE,

        CP,

        INPUT_LINE,
        LINE_PATTERN,
        MODIFY_LINE,

        AXIOM1,
        AXIOM2,
        AXIOM3,
        AXIOM4,
        MOUNTAIN,
        VALLEY,
        CUT,
        CREASE,
        UNSETTLED_VALLEY,
        UNSETTLED_MOUNTAIN,
        FLIP_LINE_TYPE,
        FOLD_UNFOLD,
        SETTLE_UNSETTLE,
        DELETE_LINE,
        SYMMETRIC,
        MIRROR,
        EDIT_VERTEX,
        ADD_VERTEX,
        DELETE_VERTEX,

        CONTOUR,
        SELECT_LINE,

        FOLDED,
        FACE,
        BASE_FACE,
        FACE_TOP,
        FACE_BOTTOM,

        FILE,
        SAVE,
        OPEN,

        RUN,
        PREVIEW,

        OPTION,

        STYLE,
        LINE_TAB,
        CLIPPING_SCALE,

        FACE_TAB,
        FACE_FRONT_COLOR,
        FACE_BACK_COLOR,
        SELECT_VERTEX,
        OFFSET,

        DIAGRAM_TAB,
        VALLEY_ARROW,
        MOUNTAIN_ARROW,
        FOLD_UNFOLD_ARROW,
        SINK_ARROW,
        PLEAT_ARROW,
    }

    public enum HINT {
        AXIOM1,
        AXIOM2,
        AXIOM3,
        AXIOM4,
    }

    public enum WARNING {
        WRONG_FACE,
        LOAD_FAILED,
        SAVE_FAILED,
        FOLD_FAILED,
        SAME_FILE_EXISTS,
        DESTROY,
    }
}