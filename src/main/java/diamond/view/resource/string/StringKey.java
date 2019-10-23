package diamond.view.resource.string;

public class StringKey {

    public enum LABEL {
        TITLE,

        CP,

        LINE_PATTERN,

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
        NONE,

        FLIP_LINE_TYPE,
        FOLD_UNFOLD,
        SETTLE_UNSETTLE,
        DELETE_LINE,
        SYMMETRIC,
        MIRROR,
        OFFSET_VERTEX_TAB,
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
        LINE_STYLE_TAB,
        FACE_STYLE_TAB,
        CLIPPING_SCALE,

        FACE_FRONT_COLOR,
        FACE_BACK_COLOR,
        AUTO_OFFSET_VERTEX,
        SELECT_ALL_VERTEX,
        OFFSET,

        VALLEY_ARROW,
        MOUNTAIN_ARROW,
        FOLD_UNFOLD_ARROW,
        FLIP_ARROW,
        ROTATE_ARROW,
        SINK_ARROW,
        PLEAT_ARROW,
        LANDMARK,
        SCALE_ARROW,
        OFFSET_ARROW,

        FACE_MANAGEMENT_TAB,
        ALTER_LINE_TYPE_TAB,
        PAINT_LINES_TAB,
        SIGNS_TAB,

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