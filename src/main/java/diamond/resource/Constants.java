/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.resource;

public class Constants {

    public static enum EditMode {

        ADD_VERTEX, CHANGE_LINE_TYPE, DELETE_LINE, DELETE_VERTEX, DIVIDE_LINE, EDIT_OUTLINE, INPUT_LINE, NONE, PICK_LINE
    };

    public static enum LineInputMode {

        BISECTOR, BY_VALUE, COPY_AND_PASTE, DIRECT_V, MIRROR, ON_V, OVERLAP_E, OVERLAP_V, PBISECTOR, // perpendicular bisector,
        SYMMETRIC_LINE, TRIANGLE_SPLIT, VERTICAL_LINE
    };

    public static enum ModelDispMode {

        FILL_ALPHA, FILL_COLOR, FILL_NONE, FILL_WHITE
    };

    public static enum ModelEditMode {

        INPUT_CROSS_LINE, NONE
    };

    public static enum SubLineInputMode {

        NONE, PICK_ANGLE, PICK_LENGTH
    };

}
