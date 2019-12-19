/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.menu;

import javax.swing.JMenuBar;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class MenuBar extends JMenuBar {
    public MenuBar(Context context) {
        add(new MenuFile(context));
        add(new MenuRun(context));
        add(new MenuOption(context));

    }
}
