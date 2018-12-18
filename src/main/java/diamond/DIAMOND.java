package diamond;

import javax.swing.JOptionPane;

import diamond.view.MainFrame;

public class DIAMOND {

    public static void main(String[] args) {
        MainFrame.getInstance();
    }

    public static void ERROR_END(String message) {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(), message, "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void outMessage(String s) {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(), s, "DIAMOND",
                JOptionPane.DEFAULT_OPTION);

    }
}
