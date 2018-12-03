/**
 * ORIPA - Origami Pattern Editor
 * Copyright (C) 2005-2009 Jun Mitani http://mitani.cs.tsukuba.ac.jp/

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package diamond.view.main;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import diamond.Config;
import diamond.doc.DocHolder;
import diamond.doc.exporter.ExporterXML;
import diamond.file.FileChooser;
import diamond.file.FileChooserFactory;
import diamond.file.FileFilterEx;
import diamond.file.FileHistory;
import diamond.file.FileVersionError;
import diamond.file.FilterDB;
import diamond.file.ImageResourceLoader;
import diamond.file.SavingAction;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.estimation.EstimationResultFrame;
import diamond.view.main.menubar.MenuEdit;
import diamond.view.main.menubar.MenuFile;
import diamond.view.main.menubar.MenuHelp;
import diamond.view.model.ModelViewFrame;
import diamond.viewsetting.main.MainFrameSettingDB;

public class MainFrame extends JFrame implements ActionListener,
        ComponentListener, WindowListener {

    private static MainFrame instance = null;

    public static FilterDB filterDB = FilterDB.getInstance();

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private FileFilterEx[] fileFilters = new FileFilterEx[] {

            filterDB.getFilter("opx"), filterDB.getFilter("pict") };

    private PainterScreen mainScreen = new PainterScreen();
    private PainterScreen secondScreen = new PainterScreen();
    private UIPanel uiPanel;

    public MainFrame() {
        addWindowListener(this);
        uiPanel = new UIPanel(mainScreen);
    }

    private void addSavingActions() {

        filterDB.getFilter("pict").setSavingAction(new SavingAction() {

            @Override
            public boolean save(String path) {
                try {
                    savePictureFile(MainFrame.getInstance(),
                            mainScreen.getCreasePatternImage(),
                            path);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
                }

                return true;
            }
        });

        filterDB.getFilter("opx").setSavingAction(new SavingAction() {

            @Override
            public boolean save(String path) {
                try {
                    saveOpxFile(path);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
                }
                return true;

            }
        });
    }

    public void saveOpxFile(String filePath) {
        ExporterXML exporter = new ExporterXML();
        exporter.export(DocHolder.getInstance().getDoc(), filePath);
        DocHolder.getInstance().getDoc().setDataFilePath(filePath);
        DocHolder.getInstance().getDoc().clearChanged();
    }

    public void savePictureFile(Component originalComponent, Image cpImage,
            String filePath)
            throws IOException {
        BufferedImage image = new BufferedImage(
                cpImage.getWidth(originalComponent),
                cpImage.getHeight(originalComponent),
                BufferedImage.TYPE_INT_RGB);

        image.getGraphics().drawImage(cpImage, 0, 0, originalComponent);

        File file = new File(filePath);
        ImageIO.write(image, filePath.substring(filePath.lastIndexOf(".") + 1),
                file);
    }

    private void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(MenuFile.getInstace());
        menuBar.add(MenuEdit.getInstance());
        menuBar.add(new MenuHelp());
        setJMenuBar(menuBar);
    }

    /**
     * Do not call directly. Please use openFile().
     *
     * @param filePath
     * @return
     */
    private String loadFile(String filePath) {

        FileFilterEx[] filters = FilterDB.getInstance().getLoadables();

        File file = new File(filePath);

        // find appropriate loader
        boolean loaded = false;
        for (FileFilterEx filter : filters) {
            if (!filter.accept(file)) {
                continue;
            }
            if (file.isDirectory()) {
                continue;
            }

            try {
                loaded = filter.getLoadingAction().load(filePath);
            } catch (FileVersionError e) {
                JOptionPane.showMessageDialog(
                        this,
                        "This file is compatible with a new version. "
                                + "Please obtain the latest version of ORIPA",
                        "Failed to load the file",
                        JOptionPane.ERROR_MESSAGE);
            }
            break;

        }

        if (!loaded) {
            return null;
        }

        return filePath;
    }

    private void loadIniFile() {
        FileHistory.loadFromFile(Config.INI_FILE_PATH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO Refactor the long, long if-else sequences!
        if (e.getSource() == MenuEdit.menuItemUndo) {
            if (PaintConfig.getMouseAction() != null) {
                PaintConfig.getMouseAction().undo(PaintContext.getInstance());
            } else {
                DocHolder.getInstance().getDoc().loadUndoInfo();
            }
            mainScreen.repaint();
        }
    }

    @Override
    public void componentHidden(ComponentEvent arg0) {
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
    }

    @Override
    public void componentResized(ComponentEvent arg0) {
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
    }

    public PainterScreen getMainScreen() {
        return this.mainScreen;
    }

    public UIPanel getUiPanel() {
        return this.uiPanel;
    }

    public void initialize() {
        setSize(Config.INITIAL_MAIN_FRAME_WIDTH,
                Config.INITIAL_MAIN_FRAME_HEIGHT);

        layoutComponents();

        setIconImage(new ImageResourceLoader()
                .loadAsIcon("icon/diamond.gif", getClass())
                .getImage());
        loadIniFile();
        addSavingActions();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateTitleText();
        buildMenuBar();
        setVisible(true);
    }

    private void layoutComponents() {

        GridLayout l = new GridLayout(2, 3);
        getContentPane().setLayout(l);
        getContentPane().add(uiPanel);
        getContentPane().add(mainScreen);
        getContentPane().add(secondScreen);
        getContentPane().add(new HintLabel());
    }

    /**
     * if filePath is null, this method opens a dialog to select the target.
     * otherwise, it tries to read data from the path.
     *
     * @param filePath
     */
    public void openFile(String filePath) {
        ModelViewFrame.getInstance().setVisible(false);
        EstimationResultFrame.getInstance().setVisible(false);

        MainFrameSettingDB.getInstance().notifyObservers();

        String path = null;

        if (filePath != null) {
            path = loadFile(filePath);
        } else {
            FileChooserFactory factory = new FileChooserFactory();
            FileChooser fileChooser = factory.createChooser(FileHistory
                    .getLastPath(), FilterDB.getInstance().getLoadables());

            fileChooser.setFileFilter(FilterDB.getInstance().getFilter("opx"));

            path = fileChooser.loadFile(this);

        }

        if (path == null) {
            path = DocHolder.getInstance().getDoc().getDataFilePath();
        } else {
            updateMenu(path);

        }

    }

    public void saveAs(String lastDirectory) {
        String path = saveFile(lastDirectory,
                DocHolder.getInstance().getDoc().getDataFileName(),
                fileFilters);

        updateMenu(path);
        updateTitleText();
    }

    public String saveFile(String homePath, FileFilterEx[] filters) {
        FileChooserFactory chooserFactory = new FileChooserFactory();
        FileChooser chooser = chooserFactory.createChooser(homePath, filters);

        String path = chooser.saveFile(this);
        if (path != null) {
            // if(path.endsWith(".opx")){
            // ORIPA.doc.setDataFilePath(path);
            // ORIPA.doc.clearChanged();
            //
            // updateMenu(path);
            // }
        } else {
            path = homePath;
        }

        return path;

    }

    public String saveFile(String directory, String fileName,
            FileFilterEx[] filters) {

        File givenFile = new File(directory, fileName);

        return saveFile(givenFile.getPath(), filters);
    }

    public void saveIniFile() {
        FileHistory.saveToFile(Config.INI_FILE_PATH);
    }

    public void updateMenu(String filePath) {
        if (filterDB.getLoadableFilterOf(filePath) == null) {
            return;
        }
        FileHistory.useFile(filePath);
        MenuFile.getInstace().addItems();
    }

    public void updateTitleText() {
        String fileName;
        if ((DocHolder.getInstance().getDoc().getDataFilePath()).equals("")) {
            fileName = ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                    "DefaultFileName");
        } else {
            fileName = DocHolder.getInstance().getDoc().getDataFileName();
        }

        setTitle(fileName + " - "
                + ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                        StringID.Main.TITLE_ID));
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowClosing(WindowEvent arg0) {

        if (DocHolder.getInstance().getDoc().isChanged()) {
            // TODO: confirm saving edited opx
            int selected = JOptionPane
                    .showConfirmDialog(
                            this,
                            "The crease pattern has been modified. Would you like to save?",
                            "Comfirm to save", JOptionPane.YES_NO_OPTION);
            if (selected == JOptionPane.YES_OPTION) {
                String path = saveFile(FileHistory.getLastDirectory(),
                        DocHolder.getInstance().getDoc().getDataFileName(),
                        fileFilters);
                if (path == null) {

                }
            }
        }

        saveIniFile();
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
    }
}
