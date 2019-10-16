/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.menu;

import javax.swing.JMenuBar;

import diamond.controller.paint.context.ModelContext;
import diamond.controller.paint.context.PaintContext;

/**
 * @author long_
 *
 */
public class MenuBar extends JMenuBar {

    public MenuBar(PaintContext paintContext, ModelContext modelContext) {
        add(new MenuFile(paintContext, modelContext));
        add(new MenuRun(paintContext));
        add(new MenuOprtion(paintContext));
    }

}
