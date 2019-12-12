/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.controller.action.VertexAddAction;
import diamond.controller.action.VertexOffsetAction;
import diamond.controller.action.VertexOffsetAutoAction;
import diamond.controller.action.VertexRemoveAction;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class TabManageVertices extends AbstractTab {
    public TabManageVertices(Context context, ButtonGroup buttonGroup) {
        setLayout(new GridLayout(1, 4));
        PaintActionButton buttonAdd = new PaintActionButton(context,
                new VertexAddAction());
        PaintActionButton buttonDelete = new PaintActionButton(context,
                new VertexRemoveAction());
        PaintActionButton buttonAuto = new PaintActionButton(context,
                new VertexOffsetAutoAction());
        PaintActionButton buttonOffset = new PaintActionButton(context,
                new VertexOffsetAction());

        addButton(buttonGroup, buttonAdd, "add_v");
        addButton(buttonGroup, buttonDelete, "delete_v");
        addButton(buttonGroup, buttonAuto, "select_v");
        addButton(buttonGroup, buttonOffset, "offset");

    }
}
