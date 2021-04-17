/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.controller.action.HalfEdgeFlipAction;
import diamond.controller.action.HalfEdgeRemoveAction;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.controller.action.HalfEdgeUnfoldAction;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class TabAlterLineType extends AbstractTab {
    public TabAlterLineType(Context context, ButtonGroup buttonGroup) {
        setLayout(new GridLayout(4, 1));
        PaintActionButton buttonFlip = new PaintActionButton(context,
                new HalfEdgeFlipAction());
        PaintActionButton buttonSettle = new PaintActionButton(context,
                new HalfEdgeSettleAction());
        PaintActionButton buttonUnfold = new PaintActionButton(context,
                new HalfEdgeUnfoldAction());
        PaintActionButton buttonDelete = new PaintActionButton(context,
                new HalfEdgeRemoveAction());

        addButton(buttonGroup, buttonFlip, "flip");
        addButton(buttonGroup, buttonSettle, "settle_unsettle");
        addButton(buttonGroup, buttonUnfold, "fold_unfold");
        addButton(buttonGroup, buttonDelete, "delete");

    }
}
