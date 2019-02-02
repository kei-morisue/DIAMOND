package diamond.view.resource.string.label;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.LABEL;

public class Resource_ja extends ListResourceBundle {

    static final String[][] strings = {
            { LABEL.MAIN_FRAME_TITLE.name(),
                    "DIAMOND : 折図専用エディタ" },
            { LABEL.FILE.name(), "ファイル" },
            { LABEL.EDIT.name(), "編集" },
            { LABEL.HELP.name(), "ヘルプ" },
            { LABEL.NEW.name(), "新規作成" },
            { LABEL.OPEN.name(), "開く" },
            { LABEL.SAVE.name(), "保存" },
            { LABEL.SAVE_AS.name(), "別名保存" },
            { LABEL.SAVE_AS_IMAGE.name(), "画像の保存" },
            { LABEL.EXPORT_DXF.name(), "DXF形式で出力" },
            { LABEL.EXPORT_OBJ.name(), "OBJ形式で出力" },
            { LABEL.EXPORT_CP.name(), "CP形式で出力" },
            { LABEL.EXPORT_SVG.name(), "SVG形式で出力" },
            { LABEL.PROPERTY.name(), "プロパティ" },
            { LABEL.EXIT.name(), "終了" },
            { LABEL.ABOUT.name(), "バージョン情報" },
            { LABEL.UNDO.name(), "戻す" },

            { LABEL.REPEAT_COPY.name(), "Repeat Copy" },
            { LABEL.CIRCLE_COPY.name(), "Circle Copy" },
            { LABEL.SELECT_ALL_LINES.name(), "Select all" },
            { LABEL.UNSELECT_ALL.name(), "Unselect all" },
            { LABEL.DELETE_SELECTED_LINES.name(),
                    "Delete Selected Lines" },

            { LABEL.CHECK_WINDOW.name(), "Foldability Validation" },

            { LABEL.AUX.name(), "Aux" },
            { LABEL.VALLEY.name(), "Valley" },
            { LABEL.MOUNTAIN.name(), "Mountain" },

            { LABEL.INPUT_LINE.name(), "Input Line" },
            { LABEL.SELECT.name(), "Select" },
            { LABEL.DELETE_LINE.name(), "Delete Line" },

            { LABEL.SHOW_GRID.name(), "Show Grid" },
            { LABEL.SHOW_MV.name(), "Show M/V Lines" },
            { LABEL.SHOW_AUX.name(), "Show Aux Lines" },

            { LABEL.CHANGE_LINE_TYPE_FROM.name(), "  from" },
            { LABEL.CHANGE_LINE_TYPE_TO.name(), "to" },
            { LABEL.DEFAULT_FILE_NAME.name(), "No_Title" },
            { LABEL.COPY_PASTE.name(), "Copy and Paste" },
            { LABEL.CUT_PASTE.name(), "Cut and Paste" },
            { LABEL.EDIT_CONTOUR.name(), "Edit Contour" },
            { LABEL.SELECT.name(), "Select" },
            { LABEL.DELETE_LINE.name(), "Delete Line" },
            { LABEL.CHANGE_LINE_TYPE.name(), "Alter Line Type" },
            { LABEL.ADD_VERTEX.name(), "Add Vertex" },
            { LABEL.DELETE_VERTEX.name(), "Delete Vertex" },
            { LABEL.SELECT_ALL_LINE.name(), "Select All" },
            { LABEL.DIAMOND_FILE.name(), "ORIPA File" },
            { LABEL.PICTURE_FILE.name(), "Picture File" },
            { LABEL.DIALOG_TITLE_SAVE.name(), "Save" },
            { LABEL.MEASURE.name(), "Measure" },
            { LABEL.FOLD.name(), "Fold..." },
            { LABEL.FULL_ESTIMATION.name(), "Full Estimation" },
            { LABEL.CHECK_WINDOW.name(), "Check Window" },
            { LABEL.GRID_SIZE_CHANGE.name(), "Set" },
            { LABEL.SHOW_VERTICES.name(), "Show Vertices" },
            { LABEL.EDIT_MODE.name(), "Edit Mode" },
            { LABEL.LINE_INPUT_MODE.name(), "Line Input Mode" },
            { LABEL.LENGTH.name(), "Length" },
            { LABEL.ANGLE.name(), "Angle" },
            { LABEL.GRID_DIVIDE_NUM.name(), "Div Num" },
            { LABEL.DISPLAY.name(), "Display" },
            { LABEL.DISPLAY.name(), "Display" },
            { LABEL.EXPORT_DXF.name(), "Export Model Line(DXF)" },
            { LABEL.INVERT.name(), "Invert" },
            { LABEL.SLIDE_FACES.name(), "Slide Faces" },
            { LABEL.DISPLAY_TYPE.name(), "Drawing type" },
            { LABEL.FILL_COLOR.name(),
                    "Fill Color: may be wrong" },
            { LABEL.FILL_WHITE.name(),
                    "Fill White: may be wrong" },
            { LABEL.FILL_ALPHA.name(), "Fill Transmission" },
            { LABEL.DRAW_LINES.name(), "Draw Lines" },
            { LABEL.MODEL_FRAME_TITLE.name(),
                    "Expected Folded Origami" },
            { LABEL.FOLDED_FRAME_TITLE.name(), "Folded Origami" },
            { LABEL.CHECK_WINDOW_TITLE.name(), "Foldability Check" },
    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
