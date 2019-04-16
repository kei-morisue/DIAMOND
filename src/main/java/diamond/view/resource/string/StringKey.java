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
        AUX,
        AUX_VALLEY,
        AUX_MOUNTAIN,
        FLIP_LINE_TYPE,
        FOLD_UNFOLD,
        SETTLE_UNSETTLE,
        DELETE_LINE,
        SYMMETRIC,
        MIRROR,

        CONTOUR,

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
        FACE_FRONT_STYLE,
        FACE_BACK_STYLE,

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