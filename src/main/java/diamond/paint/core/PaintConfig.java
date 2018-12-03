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

package diamond.paint.core;

import diamond.Config;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.segment.TwoPointSegmentAction;
import diamond.resource.Constants;

public class PaintConfig {

    public static boolean bDispCrossLine = false;

	public static boolean bDoFullEstimation = true;

public static boolean dispAuxLines = true;
    public static boolean dispMVLines = true;
    public static boolean dispVertex = false;
    //	public static Constants.EditMode editMode = Constants.EditMode.INPUT_LINE;
//    public static Constants.ModelEditMode modelEditMode = Constants.ModelEditMode.INPUT_CROSS_LINE;
//    public static Constants.EditMode preEditMode = Constants.EditMode.INPUT_LINE;
//    public static Constants.LineInputMode lineInputMode = Constants.LineInputMode.DIRECT_V;
//    public static Constants.SubLineInputMode subLineInputMode = Constants.SubLineInputMode.NONE;
    public static int gridDivNum = Config.DEFAULT_GRID_DIV_NUM;
    public static int inputLineType = diamond.value.OriLine.TYPE_RIDGE;
    public static Constants.ModelDispMode modelDispMode = Constants.ModelDispMode.FILL_ALPHA;
    public static GraphicMouseActionInterface mouseAction = new TwoPointSegmentAction();
    public static GraphicMouseActionInterface getMouseAction() {
		return mouseAction;
	}
    
    public static void setMouseAction(GraphicMouseActionInterface mouseAction) {
		PaintConfig.mouseAction = mouseAction;
	}
}
