/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import javax.swing.JMenuBar;

/**
 * @author  long_
 */
public class MenuBar extends JMenuBar {
    private static MenuBar instance = null;

    public static MenuBar getInstance() {
        if (instance == null) {
            instance = new MenuBar();
        }
        return instance;
    }

    public MenuBar() {
        add(MenuFile.getInstance());
        add(MenuEdit.getInstance());
        add(MenuHelp.getInstance());
    }

}