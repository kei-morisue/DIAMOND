/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;

import diamond.controller.Context;
import diamond.controller.action.LazyPaintAction;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class TabManageFaces extends AbstractTab {
    public TabManageFaces(Context context, ButtonGroup buttonGroup) {
        setLayout(new GridLayout(1, 2));
        PaintActionButton base = new PaintActionButton(context,
                new LazyPaintAction());//TODO
        PaintActionButton hide = new PaintActionButton(context,
                new LazyPaintAction());//TODO
        PaintActionButton swap = new PaintActionButton(context,
                new LazyPaintAction());//TODO

        addButton(buttonGroup, base, "base_face");
        addButton(buttonGroup, hide, "hide");
        addButton(buttonGroup, swap, "swap");

    }
}
