package diamond.view.resource.string.label;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.LABEL;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { LABEL.MAIN_FRAME_TITLE.name(),
                    "DIAMOND : Origami Diagram Editor" },
            { LABEL.FILE.name(), "File" },
            { LABEL.EDIT.name(), "Edit" },
            { LABEL.HELP.name(), "Help" },
            { LABEL.NEW.name(), "New" },
            { LABEL.OPEN.name(), "Open" },
            { LABEL.SAVE.name(), "Save" },
            { LABEL.SAVE_AS.name(), "Save As ..." },
            { LABEL.SAVE_AS_IMAGE.name(), "Save As Image ..." },
            { LABEL.EXPORT_DXF.name(), "Export DXF" },
            { LABEL.EXPORT_OBJ.name(), "Export OBJ" },
            { LABEL.EXPORT_CP.name(), "Export CP" },
            { LABEL.EXPORT_SVG.name(), "Export SVG" },
            { LABEL.PROPERTY.name(), "Property" },
            { LABEL.EXIT.name(), "Exit" },
            { LABEL.ABOUT.name(), "About" },
            { LABEL.UNDO.name(), "Undo" },

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
