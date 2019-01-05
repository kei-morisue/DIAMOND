package diamond.resource.string;

public class StringKey {

    public enum LABEL {
        DEFAULT_FILE_NAME,
        FILE,
        ABOUT,
        CHECK_WINDOW_TITLE,
        DELETE_SELECTED_LINES,
        EDIT,
        EXIT,
        EXPORT_CP,
        EXPORT_DXF,
        EXPORT_OBJ,
        EXPORT_SVG,
        HELP,
        NEW,
        OPEN,
        PROPERTY,
        REPEAT_COPY,
        CIRCLE_COPY,
        SAVE_AS,
        SAVE_AS_IMAGE,
        SAVE,
        SELECT_ALL_LINES,
        MAIN_FRAME_TITLE,
        UNDO,
        UNSELECT_ALL,

        DISPLAY,
        DISPLAY_TYPE,
        DRAW_LINES,
        FILL_ALPHA,
        FILL_COLOR,
        FILL_WHITE,
        INVERT,
        SLIDE_FACES,
        MODEL_FRAME_TITLE,

        FOLDED_FRAME_TITLE,

        ANGLE,
        LENGTH,
        AUX,
        CHANGE_LINE_TYPE_FROM,
        CHANGE_LINE_TYPE_TO,
        EDIT_MODE,
        GRID_DIVIDE_NUM,
        GRID_SIZE_CHANGE,

        INPUT_LINE,
        PICTURE_FILE,
        DIAMOND_FILE,

        LINE_INPUT_MODE,
        MEASURE,
        MOUNTAIN,
        VALLEY,
        SELECT,
        SHOW_AUX,
        SHOW_GRID,
        SHOW_MV,
        SHOW_VERTICES,

        CHECK_WINDOW,

        FOLD,
        FULL_ESTIMATION,
        DELETE_LINE,
        COPY_PASTE,
        CUT_PASTE,
        EDIT_CONTOUR,
        CHANGE_LINE_TYPE,
        ADD_VERTEX,
        DELETE_VERTEX,
        SELECT_ALL_LINE,

        DIALOG_TITLE_SAVE
    }

    public enum HINT {
        BISECTOR,
        CHANGE_LINE_TYPE,
        ADD_VERTEX,
        COPY_PASTE,
        CUT_PASTE,
        DELETE_VERTEX,
        EDIT_CONTOUR,
        DELETE_LINE,
        BY_VALUE,
        SCREEN_MOUSE_ACTION,
        DIRECT_V,
        MIRROR,
        ON_V,
        PERPENDICULAR_BISECTOR,
        PICK_ANGLE,
        PICK_LENGTH,
        SELECT,
        SYMMETRIC,
        TRIANGLE,
        VERTICAL,
        SELECT_ALL_LINES,
        INPUT_LINE,
    }

    public enum WARNING {
        LOAD_FAILED,
        SAVE_FAILED,
        FOLD_FAILED_DUPLICATION,
        FOLD_FAILED_WRONG_STRUCTURE,
        SAME_FILE_EXISTS,
    }
}