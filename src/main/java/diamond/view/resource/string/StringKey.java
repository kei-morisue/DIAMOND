package diamond.view.resource.string;

public class StringKey {

    public enum LABEL {
        TITLE,

        INPUT_LINE,
        LINE_PATTERN,
        MODIFY_LINE_TYPE,

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
        UNFOLD,
        SETTLE_UNSETTLE,
        DELETE_LINE,
        SYMMETRIC,

        MODEL_EDIT,
        BASE_FACE,
        FACE_TOP,
        FACE_BOTTOM,

        FILE,
        SAVE,
        OPEN,
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
    }
}