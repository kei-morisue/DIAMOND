/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class MenuBar extends JMenuBar {
    private JMenu open = new JMenu(Labels.get("open"));

    public MenuBar() {
        add(open);
    }
}
