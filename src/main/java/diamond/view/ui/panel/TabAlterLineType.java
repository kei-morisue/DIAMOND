/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.controller.action.FaceBaseAction;
import diamond.controller.action.FaceDisableAction;
import diamond.controller.action.FaceSwapAction;
import diamond.controller.action.HalfEdgeFlipAction;
import diamond.controller.action.HalfEdgeRemoveAction;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.controller.action.VertexOffsetAction;
import diamond.controller.action.VertexOffsetAutoAction;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class TabAlterLineType extends AbstractTab {
	public TabAlterLineType(Context context, ButtonGroup buttonGroup) {
		setLayout(new GridLayout(3, 3));
		PaintActionButton buttonFlip = new PaintActionButton(context, new HalfEdgeFlipAction());
		PaintActionButton buttonSettle = new PaintActionButton(context, new HalfEdgeSettleAction());
		PaintActionButton buttonDelete = new PaintActionButton(context, new HalfEdgeRemoveAction());
		PaintActionButton base = new PaintActionButton(context, new FaceBaseAction());
		PaintActionButton buttonAuto = new PaintActionButton(context, new VertexOffsetAutoAction());
		PaintActionButton hide = new PaintActionButton(context, new FaceDisableAction());
		PaintActionButton swap = new PaintActionButton(context, new FaceSwapAction());
		PaintActionButton buttonOffset = new PaintActionButton(context, new VertexOffsetAction());

		addButton(buttonGroup, buttonFlip, "flip");
		addButton(buttonGroup, buttonSettle, "settle_unsettle");
		addButton(buttonGroup, buttonDelete, "delete");
		addButton(buttonGroup, hide, "hide");
		addButton(buttonGroup, swap, "swap");
		addButton(buttonGroup, buttonOffset, "offset");
		addButton(buttonGroup, base, "base_face");
		addButton(buttonGroup, buttonAuto, "select_v");

	}
}
