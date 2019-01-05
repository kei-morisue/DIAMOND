/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar;

import javax.swing.JMenu;

import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.view.paint.menubar.help.About;

/**
 * @author long_
 *
 */
public class MenuHelp extends JMenu {
    private static MenuHelp instance = null;

    public static MenuHelp getInstance() {
        if (instance == null) {
            instance = new MenuHelp();
        }
        return instance;
    }

    private MenuHelp() {
        super(ResourceHolder.getLabelString(LABEL.HELP));
        add(About.getInstance());
    }

}
