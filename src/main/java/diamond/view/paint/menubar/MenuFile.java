/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar;

import javax.swing.JMenu;

import diamond.file.FileHistory;
import diamond.file.FilterDB;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.view.paint.menubar.file.Exit;
import diamond.view.paint.menubar.file.Export;
import diamond.view.paint.menubar.file.MRUFiles;
import diamond.view.paint.menubar.file.New;
import diamond.view.paint.menubar.file.Open;
import diamond.view.paint.menubar.file.Property;
import diamond.view.paint.menubar.file.Save;
import diamond.view.paint.menubar.file.SaveAs;

/**
 * @author long_
 *
 */
public class MenuFile extends JMenu {
    private static MenuFile instance = null;

    public static MenuFile getInstance() {
        if (instance == null) {
            instance = new MenuFile();
        }
        return instance;
    }

    private MenuFile() {
        super(ResourceHolder.getLabelString(LABEL.FILE));
        addItems();
    }

    private void addItems() {
        removeAll();
        add(New.getInstance());
        add(Open.getInstance());
        add(Save.getInstance());
        add(SaveAs.getInstance());
        new Export(this);
        addSeparator();
        add(Property.getInstance());
        addSeparator();
        new MRUFiles(this);
        addSeparator();
        add(Exit.getInstance());

    }

    public void updateMRUItems(String filePath) {
        if (FilterDB.getInstance().getLoadableFilterOf(filePath) == null) {
            return;
        }
        FileHistory.useFile(filePath);
        addItems();
    }
}
