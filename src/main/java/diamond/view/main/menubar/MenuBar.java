/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;

/**
 * @author  long_
 */
public class MenuBar extends JMenuBar {
    private JMenu menuEdit;
    /**
     *
     */
    private JMenu menuFile;
    private JMenu menuHelp;

    /**
     *
     */
    public MenuBar() {
        this.menuFile = new JMenu(
                ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                        StringID.Main.FILE_ID));
        this.menuEdit = new JMenu(
                ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                        StringID.Main.EDIT_ID));
        this.menuHelp = new JMenu(
                ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                        StringID.Main.HELP_ID));
    }

    public JMenu getMenuEdit() {
        return this.menuEdit;
    }

    public JMenu getMenuFile() {
        return this.menuFile;
    }

    public JMenu getMenuHelp() {
        return this.menuHelp;
    }
}