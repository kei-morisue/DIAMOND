/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.menu;

import javax.swing.JMenuBar;

import diamond.controller.paint.context.Context;

/**
 * @author long_
 *
 */
public class MenuBar extends JMenuBar {

    public MenuBar(Context context) {
        add(new MenuFile(context));
        add(new MenuRun(context));
        add(new MenuOption(context));
    }

}
