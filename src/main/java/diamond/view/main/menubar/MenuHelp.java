/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import javax.swing.JMenu;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.menubar.help.About;

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

    public MenuHelp() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.HELP_ID));
        add(About.getInstance());
    }

}
