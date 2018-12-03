/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import diamond.Config;
import diamond.file.FileHistory;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;
import diamond.view.main.menubar.file.New;
import diamond.view.main.menubar.file.Open;
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

    private static ResourceHolder res = ResourceHolder.getInstance();
    public static JMenuItem menuItemSaveAsImage = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.SAVE_AS_IMAGE_ID));

    public static JMenuItem menuItemExportDXF = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_DXF_ID));
    public static JMenuItem menuItemExportOBJ = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_OBJ_ID));
    public static JMenuItem menuItemExportCP = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_CP_ID));
    public static JMenuItem menuItemExportSVG = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_SVG_ID));
    public static JMenuItem menuItemProperty = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.PROPERTY_ID));
    public static JMenuItem[] MRUFilesMenuItem = new JMenuItem[Config.MRUFILE_NUM];

    public static JMenuItem menuItemExit = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXIT_ID));

    public MenuFile() {
        super(res.getString(ResourceKey.LABEL, StringID.Main.FILE_ID));
        addActionListeners();
        addItems();
    }

    private void addActionListeners() {
        MainFrame mainFrame = MainFrame.getInstance();
        menuItemSaveAsImage.addActionListener(mainFrame);
        menuItemExit.addActionListener(mainFrame);
        menuItemExportDXF.addActionListener(mainFrame);
        menuItemExportOBJ.addActionListener(mainFrame);
        menuItemExportCP.addActionListener(mainFrame);
        menuItemExportSVG.addActionListener(mainFrame);
        menuItemProperty.addActionListener(mainFrame);
        for (int i = 0; i < Config.MRUFILE_NUM; i++) {
            MRUFilesMenuItem[i] = new JMenuItem();
            MRUFilesMenuItem[i].addActionListener(mainFrame);
        }
    }

    public void addItems() {
        removeAll();
        add(New.getInstance());
        add(Open.getInstance());
        add(Save.getInstance());
        add(SaveAs.getInstance());
        add(menuItemSaveAsImage);
        add(menuItemExportDXF);
        add(menuItemExportOBJ);
        add(menuItemExportCP);
        add(menuItemExportSVG);
        addSeparator();
        add(menuItemProperty);
        addSeparator();

        int i = 0;
        for (String path : FileHistory.getHistory()) {
            MRUFilesMenuItem[i].setText(path);
            add(MRUFilesMenuItem[i]);

            i++;
        }
        while (i < MRUFilesMenuItem.length) {
            MRUFilesMenuItem[i].setText("");
            i++;
        }

        addSeparator();
        add(menuItemExit);

    }
}
