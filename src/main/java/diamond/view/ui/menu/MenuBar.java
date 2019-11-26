/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class MenuBar extends JMenuBar {
    private JMenu open = new JMenu(Labels.get("main_menu_open"));

    public MenuBar(Context context) {
        add(open);
    }
}
