package diamond;

import javax.swing.JOptionPane;

import diamond.view.paint.PaintFrame;
import diamond.view.paint.menubar.file.MRUFiles;

public class DIAMOND {

    public static void main(String[] args) {
        PaintFrame.getInstance();
        study();

    }

    private static void study() {
        if (MRUFiles.menuItems[0] != null) {
            MRUFiles.menuItems[0].doClick();
        }
    }

    public static void ERROR_END(String message) {
        JOptionPane.showMessageDialog(
                PaintFrame.getInstance(), message, "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void outMessage(String s) {
        JOptionPane.showMessageDialog(
                PaintFrame.getInstance(), s, "DIAMOND",
                JOptionPane.DEFAULT_OPTION);

    }
}
