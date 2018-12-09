/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.file;

import java.awt.Component;
import java.io.File;

import javax.swing.JOptionPane;

import diamond.doc.DocHolder;
import diamond.view.main.MainFrame;
import diamond.view.main.menubar.MenuFile;

/**
 * @author long_
 *
 */
public class FileIOUtil {

    private FileIOUtil() {
    }

    public static String exportFile(String homePath, FileFilterEx[] filters) {
        FileChooserFactory chooserFactory = new FileChooserFactory();
        FileChooser chooser = chooserFactory.createChooser(homePath, filters);

        String path = chooser.saveFile(MainFrame.getInstance());
        if (path != null) {

        } else {
            path = homePath;
        }

        return path;
    }

    public static String exportFile(String homePath, String fileName,
            FileFilterEx filter) {
        return exportFile(homePath, fileName, new FileFilterEx[] { filter });
    }

    public static String exportFile(String directory, String fileName,
            FileFilterEx[] filters) {
        File givenFile = new File(directory, fileName);
        return exportFile(givenFile.getPath(), filters);
    }

    /**
     * if filePath is null, this method opens a dialog to select the target.
     * otherwise, it tries to read data from the path.
     *
     * @param filePath
     */
    public static void openFile(Component originalComponent, String filePath) {
        String path = null;

        if (filePath != null) {
            path = loadFile(originalComponent, filePath);
        } else {
            FileChooserFactory factory = new FileChooserFactory();
            FileChooser fileChooser = factory.createChooser(FileHistory
                    .getLastPath(), FilterDB.getInstance().getLoadables());
            fileChooser.setFileFilter(FilterDB.getInstance().getFilter("opx"));
            fileChooser.setFileFilter(FilterDB.getInstance().getFilter("cp"));
            path = fileChooser.loadFile(originalComponent);
        }

        if (path == null) {
            path = DocHolder.getDoc().getDataFilePath();
        } else {
            MenuFile.getInstance().updateMRUItems(path);

        }

    }

    //TODO Needs tobe refactored
    private static String loadFile(Component originalComponent,
            String filePath) {

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
                        originalComponent,
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
}
