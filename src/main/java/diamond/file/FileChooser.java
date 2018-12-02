package diamond.file;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;

/**
 *
 * @author OUCHI Koji
 *
 */

public class FileChooser extends JFileChooser {

    /**
     *
     */
    private static final long serialVersionUID = 4700305827321319095L;

    public FileChooser() {

        super();
    }

    public FileChooser(String path) {
        super(path);


//		File file = new File(trimmedPath);
        File file = new File(path);
        this.setSelectedFile(file);

        System.out.println(path);
    }

    /**
     * don't use this!
     */
    @Deprecated
    public void addChoosableFileFilter(FileFilter filter) {

    }

    public void addChoosableFileFilter(FileFilterEx filter) {
        // TODO Auto-generated method stub
        super.addChoosableFileFilter(filter);
    }

    public String replaceExtension(String path, String ext) {

        String path_new;

        path_new = path.replaceAll("\\.\\w+$", "");
        path_new += ext;

        return path_new;
    }

    /**
     * this method does not change {@code path}.
     * @param path
     * @param ext ex) ".png"
     * @return path string with new extension
     */
    public String correctExtension(String path, String[] extensions) {

        String path_new = new String(path);

        boolean isCorrect = false;
        for (int i = 0; i < extensions.length; i++) {
            if (path.endsWith(extensions[i])) {
                isCorrect = true;
                break;
            }
        }

        if (isCorrect == false) {
            path_new = replaceExtension(path_new, extensions[0]);
        }

        return path_new;
    }


    public String saveFile(Component parent) {


        if (JFileChooser.APPROVE_OPTION != this.showSaveDialog(parent)) {
            return null;
        }

        String filePath = null;

        try {

            FileFilterEx filter = ((FileFilterEx) this.getFileFilter());
            String[] extensions = filter.getExtensions();

            filePath = correctExtension(
                    this.getSelectedFile().getPath(), extensions);

            if (filePath == null) {
                throw new IllegalArgumentException(
                        "wrong extension of selected name");
            }

            File file = new File(filePath);
            if (file.exists()) {
                if (JOptionPane.showConfirmDialog(
                        null,
                        ResourceHolder.getInstance().getString(
                                ResourceKey.WARNING,
                                "Warning_SameNameFileExist"),
                        ResourceHolder.getInstance().getString(
                                ResourceKey.LABEL, "DialogTitle_FileSave"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) {
                    return null;
                }
            }

            filter.getSavingAction().save(filePath);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    parent, e.toString(),
                    ResourceHolder.getInstance().getString(ResourceKey.WARNING,
                            "Error_FileSaveFailed"),
                    JOptionPane.ERROR_MESSAGE);
        }

        return filePath;
    }

    public String loadFile(Component parent) {
        String filePath = null;

        if (JFileChooser.APPROVE_OPTION == this.showOpenDialog(parent)) {
            try {
                filePath = this.getSelectedFile().getPath();
                FileFilterEx filter = ((FileFilterEx) this.getFileFilter());

                filter.getLoadingAction().load(filePath);
            } catch (FileVersionError v_err) {
                JOptionPane.showMessageDialog(
                        this, "This file is compatible with a new version. "
                                + "Please obtain the latest version of ORIPA",
                        "Failed to load the file",
                        JOptionPane.ERROR_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this, e.toString(),
                        ResourceHolder.getInstance().getString(
                                ResourceKey.WARNING, "Error_FileLoadFailed"),
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return filePath;
    }

}
