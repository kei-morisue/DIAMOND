package diamond.view.resource.string.hint;

import java.util.ListResourceBundle;

import diamond.view.resource.string.StringKey.HINT;

public class Resource_en extends ListResourceBundle {

    static final String[][] strings = {
            { HINT.DIRECT_V.name(),
                    "Specify two end points.[Ctrl] allows to pick any point on an edge." },

            { HINT.ON_V.name(),
                    "Specify two points that lie on the line" },

            { HINT.SYMMETRIC.name(),
                    "Input a symmetrical segment. 1st, 2nd are for target. 2nd, 3rd are for base.+[Ctrl] continues automatically." },

            { HINT.TRIANGLE.name(),
                    "Specify 3 points to input 3 segments to incenter." },

            { HINT.BISECTOR.name(),
                    "Input a bisector. Specify 3 points and a segment." },

            { HINT.VERTICAL.name(),
                    "Input a vertical line. Specify a point and a line." },

            { HINT.MIRROR.name(),
                    "Mirror copy. Pick target segments and [Ctrl]+Click for the base segment." },

            { HINT.BY_VALUE.name(),
                    "Input length and angle, then specify the start point. Push [Mesure] button to get value from segment." },

            { HINT.PICK_LENGTH.name(),
                    "Mesure distance between 2 points." },

            { HINT.PICK_ANGLE.name(),
                    "Mesure angle. Specify 3 points" },

            { HINT.CHANGE_LINE_TYPE.name(),
                    "Change type of a segment. Pick a segment." },

            { HINT.DELETE_LINE.name(),
                    "Delete a segment. Pick a segment" },

            { HINT.ADD_VERTEX.name(), "Add vertex on a segment." },

            { HINT.DELETE_VERTEX.name(),
                    "Delete vertex that does not change the structure." },

            { HINT.EDIT_CONTOUR.name(),
                    "Edit contour. The contour must be convex." },

            { HINT.PERPENDICULAR_BISECTOR.name(),
                    "Input Perpendicular Bisector of two vertices. Select two vertices by left click." },

            { HINT.SELECT.name(),
                    "Select/UnSelect Lines by Left Click or Left Drag" },

            { HINT.SELECT_ALL_LINES.name(),
                    "Select/UnSelect Lines by Left Click or Left Drag" },

            { HINT.COPY_PASTE.name(),
                    "Left Click for Paste. [Ctrl] allows you to change the origin of pasting." },
            { HINT.CUT_PASTE.name(),
                    "Left Click for Paste. [Ctrl] allows you to change the origin of pasting." },
            { HINT.SCREEN_MOUSE_ACTION.name(),
                    "    L: Rotate R:Move Wheel:Zoom " },
            { HINT.INPUT_LINE.name(),
                    "Select a line type and paint pattern." },

    };

    @Override
    protected Object[][] getContents() {
        return strings;
    }

}
