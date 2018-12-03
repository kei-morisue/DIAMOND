/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.Config;
import diamond.file.FileHistory;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class MenuFile extends JMenu {

    private static ResourceHolder res = ResourceHolder.getInstance();
    private JMenuItem menuItemClear = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.NEW_ID));
    private JMenuItem menuItemOpen = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.OPEN_ID));
    private JMenuItem menuItemSave = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.SAVE_ID));
    private JMenuItem menuItemSaveAs = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.SAVE_AS_ID));
    private JMenuItem menuItemSaveAsImage = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.SAVE_AS_IMAGE_ID));

    private JMenuItem menuItemExportDXF = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_DXF_ID));
    private JMenuItem menuItemExportOBJ = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_OBJ_ID));
    private JMenuItem menuItemExportCP = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_CP_ID));
    private JMenuItem menuItemExportSVG = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXPORT_SVG_ID));
    private JMenuItem menuItemProperty = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.PROPERTY_ID));
    private JMenuItem[] MRUFilesMenuItem = new JMenuItem[Config.MRUFILE_NUM];

    private JMenuItem menuItemExit = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.EXIT_ID));


    public MenuFile(MainFrame mainFrame) {
        super(res.getString(ResourceKey.LABEL, StringID.Main.FILE_ID));
        addActionListeners(mainFrame);
        addAccelerators();
        addItems();
    }

    private void addAccelerators() {
        menuItemClear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
    }

    private void addActionListeners(MainFrame mainFrame) {
        menuItemClear.addActionListener(mainFrame);
        menuItemOpen.addActionListener(mainFrame);
        menuItemSave.addActionListener(mainFrame);
        menuItemSaveAs.addActionListener(mainFrame);
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
        add(menuItemClear);
        add(menuItemOpen);
        add(menuItemSave);
        add(menuItemSaveAs);
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

    public JMenuItem getMenuItemClear() {
        return this.menuItemClear;
    }

    public JMenuItem getMenuItemOpen() {
        return this.menuItemOpen;
    }

    public JMenuItem getMenuItemSave() {
        return this.menuItemSave;
    }

    public JMenuItem getMenuItemSaveAs() {
        return this.menuItemSaveAs;
    }

    public JMenuItem getMenuItemSaveAsImage() {
        return this.menuItemSaveAsImage;
    }

    public JMenuItem getMenuItemExportDXF() {
        return this.menuItemExportDXF;
    }

    public JMenuItem getMenuItemExportOBJ() {
        return this.menuItemExportOBJ;
    }

    public JMenuItem getMenuItemExportCP() {
        return this.menuItemExportCP;
    }

    public JMenuItem getMenuItemExportSVG() {
        return this.menuItemExportSVG;
    }

    public JMenuItem getMenuItemProperty() {
        return this.menuItemProperty;
    }

    public JMenuItem[] getMRUFilesMenuItem() {
        return this.MRUFilesMenuItem;
    }

    public JMenuItem getMenuItemExit() {
        return this.menuItemExit;
    }

    //    /* (非 Javadoc)
    //     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
    //     */
    //    @Override
    //    public void actionPerformed(ActionEvent e) {
    //        // String lastPath = fileHistory.getLastPath();
    //        String lastDirectory = fileHistory.getLastDirectory();
    //
    //        if (e.getSource() == menuItemOpen) {
    //            openFile(null);
    //            mainScreen.repaint();
    //            updateTitleText();
    //        } else if (e.getSource() == menuItemSave
    //                && !DocHolder.getInstance().getDoc().getDataFilePath()
    //                        .equals("")) {
    //            saveOpxFile(DocHolder.getInstance().getDoc().getDataFilePath());
    //
    //        } else if (e.getSource() == menuItemSaveAs
    //                || e.getSource() == menuItemSave) {
    //
    //            String path = saveFile(lastDirectory,
    //                    DocHolder.getInstance().getDoc().getDataFileName(),
    //                    fileFilters);
    //
    //            updateMenu(path);
    //            updateTitleText();
    //
    //        } else if (e.getSource() == menuItemSaveAsImage) {
    //
    //            saveFile(lastDirectory,
    //                    DocHolder.getInstance().getDoc().getDataFileName(),
    //                    new FileFilterEx[] { filterDB.getFilter("pict") });
    //
    //        } else if (e.getSource() == menuItemExportDXF) {
    //            exportFile("dxf");
    //        } else if (e.getSource() == menuItemExportOBJ) {
    //            exportFile("obj");
    //        } else if (e.getSource() == menuItemExportCP) {
    //            exportFile("cp");
    //        } else if (e.getSource() == menuItemExportSVG) {
    //            exportFile("svg");
    //        } else if (e.getSource() == menuItemChangeOutline) {
    //            // Globals.preEditMode = Globals.editMode;
    //            // Globals.editMode = Constants.EditMode.EDIT_OUTLINE;
    //
    //            // Globals.setMouseAction(new EditOutlineAction());
    //
    //        } else if (e.getSource() == menuItemExit) {
    //            saveIniFile();
    //            System.exit(0);
    //        } else if (e.getSource() == menuItemUndo) {
    //            if (PaintConfig.getMouseAction() != null) {
    //                PaintConfig.getMouseAction().undo(mouseContext);
    //            } else {
    //                DocHolder.getInstance().getDoc().loadUndoInfo();
    //            }
    //            mainScreen.repaint();
    //        } else if (e.getSource() == menuItemClear) {
    //            DocHolder.getInstance()
    //                    .setDoc(new Doc(Constants.DEFAULT_PAPER_SIZE));
    //            DIAMOND.modelFrame.repaint();
    //
    //            DIAMOND.modelFrame.setVisible(false);
    //            DIAMOND.renderFrame.setVisible(false);
    //
    //            screenSetting.setGridVisible(true);
    //            screenSetting.notifyObservers();
    //
    //            // ORIPA.mainFrame.uiPanel.dispGridCheckBox.setSelected(true);
    //            updateTitleText();
    //        } else if (e.getSource() == menuItemAbout) {
    //            JOptionPane.showMessageDialog(this, infoString,
    //                    res.getString(ResourceKey.LABEL,
    //                            "Title"),
    //                    JOptionPane.INFORMATION_MESSAGE);
    //        } else if (e.getSource() == menuItemProperty) {
    //            PropertyDialog dialog = new PropertyDialog(this);
    //            dialog.setValue();
    //            Rectangle rec = getBounds();
    //            dialog.setLocation(
    //                    (int) (rec.getCenterX() - dialog.getWidth() / 2),
    //                    (int) (rec.getCenterY() - dialog.getHeight() / 2));
    //            dialog.setModal(true);
    //            dialog.setVisible(true);
    //        }
    //    }
    //
    //    public void updateMenu(String filePath) {
    //
    //        if (filterDB.getLoadableFilterOf(filePath) == null) {
    //            return;
    //        }
    //
    //        fileHistory.useFile(filePath);
    //
    //        buildMenuFile();
    //    }
    //    private void saveOpxFile(String filePath) {
    //        ExporterXML exporter = new ExporterXML();
    //        exporter.export(DocHolder.getInstance().getDoc(), filePath);
    //        DocHolder.getInstance().getDoc().setDataFilePath(filePath);
    //
    //        updateMenu(filePath);
    //
    //        DocHolder.getInstance().getDoc().clearChanged();
    //    }
    //
    //    private void savePictureFile(Image cpImage, String filePath)
    //            throws IOException {
    //        BufferedImage image = new BufferedImage(cpImage.getWidth(this),
    //                cpImage.getHeight(this), BufferedImage.TYPE_INT_RGB);
    //
    //        image.getGraphics().drawImage(cpImage, 0, 0, this);
    //
    //        MenuFile file = new MenuFile(filePath);
    //        ImageIO.write(image, filePath.substring(filePath.lastIndexOf(".") + 1),
    //                file);
    //    }
    //
    //    public void updateTitleText() {
    //        String fileName;
    //        if ((DocHolder.getInstance().getDoc().getDataFilePath()).equals("")) {
    //            fileName = res.getString(ResourceKey.LABEL,
    //                    "DefaultFileName");
    //        } else {
    //            fileName = DocHolder.getInstance().getDoc().getDataFileName();
    //        }
    //
    //        setTitle(fileName + " - "
    //                + res.getString(ResourceKey.LABEL,
    //                        StringID.Main.TITLE_ID));
    //    }
    //
    //    private String saveFile(String directory, String fileName,
    //            FileFilterEx[] filters) {
    //
    //        java.io.File givenFile = new java.io.File(directory, fileName);
    //
    //        return saveFile(givenFile.getPath(), filters);
    //    }
    //
    //    private String saveFile(String homePath, FileFilterEx[] filters) {
    //        FileChooserFactory chooserFactory = new FileChooserFactory();
    //        FileChooser chooser = chooserFactory.createChooser(homePath, filters);
    //
    //        String path = chooser.saveFile(this);
    //        if (path != null) {
    //            // if(path.endsWith(".opx")){
    //            // ORIPA.doc.setDataFilePath(path);
    //            // ORIPA.doc.clearChanged();
    //            //
    //            // updateMenu(path);
    //            // }
    //        } else {
    //            path = homePath;
    //        }
    //
    //        return path;
    //
    //    }
    //
    //    public void exportFile(String ext) {
    //        Doc document = DocHolder.getInstance().getDoc();
    //        CreasePattern creasePattern = document.getCreasePattern();
    //        OrigamiModel origamiModel = document.getOrigamiModel();
    //
    //        boolean hasModel = origamiModel.hasModel();
    //
    //        OrigamiModelFactory modelFactory = new OrigamiModelFactory();
    //        origamiModel = modelFactory.buildOrigami(creasePattern,
    //                document.getPaperSize(), true);
    //        document.setOrigamiModel(origamiModel);
    //
    //        if ("obj".equals(ext) == false) {
    //
    //        } else if (!hasModel && !origamiModel.isProbablyFoldable()) {
    //
    //            JOptionPane.showConfirmDialog(null,
    //                    "Warning: Building a set of polygons from crease pattern "
    //                            + "was failed.",
    //                    "Warning",
    //                    JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
    //        }
    //
    //        saveFile(null, new FileFilterEx[] { filterDB.getFilter(ext) });
    //    }
    //    private void openFile(String filePath) {
    //        DIAMOND.modelFrame.setVisible(false);
    //        DIAMOND.renderFrame.setVisible(false);
    //
    //        screenSetting.setGridVisible(false);
    //        screenSetting.notifyObservers();
    //
    //        // ORIPA.mainFrame.uiPanel.dispGridCheckBox.setSelected(false);
    //
    //        String path = null;
    //
    //        if (filePath != null) {
    //            path = loadFile(filePath);
    //        } else {
    //            FileChooserFactory factory = new FileChooserFactory();
    //            FileChooser fileChooser = factory.createChooser(fileHistory
    //                    .getLastPath(), FilterDB.getInstance().getLoadables());
    //
    //            fileChooser.setFileFilter(FilterDB.getInstance().getFilter("opx"));
    //
    //            path = fileChooser.loadFile(this);
    //
    //        }
    //
    //        if (path == null) {
    //            path = DocHolder.getInstance().getDoc().getDataFilePath();
    //        } else {
    //            updateMenu(path);
    //
    //        }
    //
    //    }
    //
    //    /**
    //     * Do not call directly. Please use openFile().
    //     *
    //     * @param filePath
    //     * @return
    //     */
    //    private String loadFile(String filePath) {
    //
    //        FileFilterEx[] filters = FilterDB.getInstance().getLoadables();
    //
    //        MenuFile file = new MenuFile(filePath);
    //
    //        // find appropriate loader
    //        boolean loaded = false;
    //        for (FileFilterEx filter : filters) {
    //            if (!filter.accept(file)) {
    //                continue;
    //            }
    //            if (file.isDirectory()) {
    //                continue;
    //            }
    //
    //            try {
    //                loaded = filter.getLoadingAction().load(filePath);
    //            } catch (FileVersionError e) {
    //                JOptionPane.showMessageDialog(
    //                        this,
    //                        "This file is compatible with a new version. "
    //                                + "Please obtain the latest version of ORIPA",
    //                        "Failed to load the file",
    //                        JOptionPane.ERROR_MESSAGE);
    //            }
    //            break;
    //
    //        }
    //
    //        if (!loaded) {
    //            return null;
    //        }
    //
    //        return filePath;
    //    }
    //
    //    private void saveIniFile() {
    //        fileHistory.saveToFile(iniFilePath);
    //    }
}
