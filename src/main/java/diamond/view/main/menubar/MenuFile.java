/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import javax.swing.JMenu;

import diamond.file.FileHistory;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.menubar.file.Exit;
import diamond.view.main.menubar.file.Export;
import diamond.view.main.menubar.file.MRUFiles;
import diamond.view.main.menubar.file.New;
import diamond.view.main.menubar.file.Open;
import diamond.view.main.menubar.file.Property;
import diamond.view.main.menubar.file.Save;
import diamond.view.main.menubar.file.SaveAs;

/**
 * @author long_
 *
 */
public class MenuFile extends JMenu {
    private static MenuFile instance = null;

    public static MenuFile getInstace() {
        if (instance == null) {
            instance = new MenuFile();
        }
        return instance;

    }

    public MenuFile() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.FILE_ID));
        addItems();
    }

    private void addExportItems() {
        new Export();
        add(Export.menuItemExportDXF);
        add(Export.menuItemExportOBJ);
        add(Export.menuItemExportCP);
        add(Export.menuItemExportSVG);
    }

    private void addMRUItems() {
        int i = 0;
        new MRUFiles();
        for (String path : FileHistory.getHistory()) {
            MRUFiles.menuItems[i].setText(path);
            add(MRUFiles.menuItems[i]);
            i++;
        }
        while (i < MRUFiles.menuItems.length) {
            MRUFiles.menuItems[i].setText("");
            i++;
        }
    }

    public void addItems() {
        removeAll();//TODO seems needless...
        add(New.getInstance());
        add(Open.getInstance());
        add(Save.getInstance());
        add(SaveAs.getInstance());
        addExportItems();
        addSeparator();
        add(Property.getInstance());
        addSeparator();
        addMRUItems();
        addSeparator();
        add(Exit.getInstance());

    }
}
