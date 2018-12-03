package diamond;

import javax.swing.JOptionPane;

import diamond.view.estimation.EstimationResultFrame;
import diamond.view.main.MainFrame;
import diamond.view.model.ModelViewFrame;

public class DIAMOND {

    public static int tmpInt;

    public static void ERROR_END(String message) {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(), message, "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        MainFrame.getInstance().initialize();
        ModelViewFrame.getInstance().initialize();
        EstimationResultFrame.getInstance().initialize();
    }

    public static void outMessage(String s) {
        JOptionPane.showMessageDialog(
                MainFrame.getInstance(), s, "DIAMOND",
                JOptionPane.DEFAULT_OPTION);

    }
}
