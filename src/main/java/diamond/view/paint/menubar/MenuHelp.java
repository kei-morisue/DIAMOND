/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar;

import javax.swing.JMenu;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
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
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.HELP_ID));
        add(About.getInstance());
    }

}
