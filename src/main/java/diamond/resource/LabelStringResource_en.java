package diamond.resource;

import java.util.ListResourceBundle;

import diamond.Version;

public class LabelStringResource_en extends ListResourceBundle {

    static final Object[][] strings = {
            { StringID.Main.TITLE_ID,
                    "DIAMOND : Origami Diagram Editor v" + Version.VERSION },
            { StringID.Main.FILE_ID, "File" },
            { StringID.Main.EDIT_ID, "Edit" },
            { StringID.Main.HELP_ID, "Help" },
            { StringID.Main.NEW_ID, "New" },
            { StringID.Main.OPEN_ID, "Open" },
            { StringID.Main.SAVE_ID, "Save" },
            { StringID.Main.SAVE_AS_ID, "Save As ..." },
            { StringID.Main.SAVE_AS_IMAGE_ID, "Save As Image ..." },
            { StringID.Main.EXPORT_DXF_ID, "Export DXF" },
            { StringID.Main.EXPORT_OBJ_ID, "Export OBJ" },
            { StringID.Main.EXPORT_CP_ID, "Export CP" },
            { StringID.Main.EXPORT_SVG_ID, "Export SVG" },
            { StringID.Main.PROPERTY_ID, "Property" },
            { StringID.Main.EXIT_ID, "Exit" },
            { StringID.Main.ABOUT_ID, "About" },
            { StringID.Main.UNDO_ID, "Undo" },

            { StringID.Main.REPEAT_COPY_ID, "Repeat Copy" },
            { StringID.Main.CIRCLE_COPY_ID, "Circle Copy" },
            { StringID.Main.SELECT_ALL_ID, "Select all" },
            { StringID.Main.UNSELECT_ALL_ID, "Unselect all" },
            { StringID.Main.DELETE_SELECTED_LINES_ID, "Delete Selected Lines" },

            { StringID.UI.AUX_ID, "Aux" },
            { StringID.UI.VALLEY_ID, "Valley" },
            { StringID.UI.MOUNTAIN_ID, "Mountain" },

            { StringID.UI.INPUT_LINE_ID, "Input Line" },
            { StringID.UI.SELECT_ID, "Select" },
            { StringID.UI.DELETE_LINE_ID, "Delete Line" },

            { StringID.UI.SHOW_GRID_ID, "Show Grid" },
            { StringID.UI.SHOW_MV_ID, "Show M/V Lines" },
            { StringID.UI.SHOW_AUX_ID, "Show Aux Lines" },

            { StringID.UI.CHANGE_LINE_TYPE_FROM_ID, "  from" },
            { StringID.UI.CHANGE_LINE_TYPE_TO_ID, "to" },
            //---------------------------------------------------------
            // Default IDs
            { StringID.Default.FILE_NAME_ID, "No_Title" },

            //---------------------------------------------------------
            // Integrated IDs
            { StringID.COPY_PASTE_ID, "Copy and Paste" },
            { StringID.CUT_PASTE_ID, "Cut and Paste" },
            { StringID.EDIT_CONTOUR_ID, "Edit Contour" },
            { StringID.SELECT_ID, "Select" },
            { StringID.DELETE_LINE_ID, "Delete Line" },
            { StringID.CHANGE_LINE_TYPE_ID, "Change Line Type" },
            { StringID.ADD_VERTEX_ID, "Add Vertex" },
            { StringID.DELETE_VERTEX_ID, "Delete Vertex" },
            { StringID.SELECT_ALL_LINE_ID, "Select All" },
            { StringID.ORIPA_FILE_ID, "ORIPA File" },
            { StringID.PICTURE_FILE_ID, "Picture File" },
            { StringID.DIALOG_TITLE_SAVE_ID, "Save" },
            { StringID.UI.MEASURE_ID, "Measure" },
            { StringID.UI.FOLD_ID, "Fold..." },
            { StringID.UI.FULL_ESTIMATION_ID, "Full Estimation" },
            { StringID.UI.CHECK_WINDOW_ID, "Check Window" },

            { StringID.UI.GRID_SIZE_CHANGE_ID, "Set" },
            { StringID.UI.SHOW_VERTICES_ID, "Show Vertices" },
            { StringID.UI.EDIT_MODE_ID, "Edit Mode" },
            { StringID.UI.LINE_INPUT_MODE_ID, "Line Input Mode" },
            { StringID.UI.LENGTH_ID, "Length" },
            { StringID.UI.ANGLE_ID, "Angle" },
            { StringID.UI.GRID_DIVIDE_NUM_ID, "Div Num" },

            //---------------------------------------------------------
            // Model Menu IDs
            { StringID.ModelMenu.DISPLAY_ID, "Display" },
            { StringID.ModelMenu.DISPLAY_ID, "Display" },
            { StringID.ModelMenu.EXPORT_DXF_ID, "Export Model Line(DXF)" },
            { StringID.ModelMenu.INVERT_ID, "Invert" },
            { StringID.ModelMenu.SLIDE_FACES_ID, "Slide Faces" },
            { StringID.ModelMenu.DIRECTION_BASIC_ID,
                    "    L: Rot R:Move Wheel:Zoom " },
            { StringID.ModelMenu.DISPLAY_TYPE_ID, "Drawing type" },
            { StringID.ModelMenu.FILL_COLOR_ID, "Fill Color: may be wrong" },
            { StringID.ModelMenu.FILL_WHITE_ID, "Fill White: may be wrong" },
            { StringID.ModelMenu.FILL_ALPHA_ID, "Fill Transmission" },
            { StringID.ModelMenu.DRAW_LINES_ID, "Draw Lines" },
            { StringID.ModelMenu.TITLE_ID, "Expected Folded Origami" }

    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
