package diamond;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import diamond.view.estimation.EstimationResultFrame;
import diamond.view.main.MainFrame;
import diamond.view.model.ModelViewFrame;
import diamond.view.model.ModelViewFrame3D;


public class DIAMOND {

    public static MainFrame mainFrame;
    public static ModelViewFrame modelFrame;

    public static ModelViewFrame3D modelFrame3D;
    public static int tmpInt;
    public static EstimationResultFrame renderFrame;

    private static void buildMainFrame() {
        int uiPanelWidth = 0;
        int mainFrameWidth = 1000;
        int mainFrameHeight = 800;
        mainFrame = new MainFrame();
        mainFrame.setSize(uiPanelWidth + mainFrameWidth, mainFrameHeight);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.updateTitleText();
        mainFrame.initialize();
        mainFrame.setVisible(true);
    }

    private static void buildModelFrame() {
        int modelFrameWidth = 400;
        int modelFrameHeight = 400;
        modelFrame = new ModelViewFrame();
        modelFrame.setSize(modelFrameWidth, modelFrameHeight);
        mainFrame.setLocationRelativeTo(null);
        modelFrame.setVisible(false);
    }

    private static void buildRenderFrame() {
        renderFrame = new EstimationResultFrame();
        renderFrame.setVisible(false);
    }

    public static void main(String[] args) {
        // Construction of the main frame
        buildMainFrame();
        // Expected folded origami frame (x-ray)
        buildModelFrame();
        // "Folded origami" frame. Estimation of the folded form.
        buildRenderFrame();
        // "Check Window". Check inputed data.
    }

    public static void ERROR_END(String message) {
        JOptionPane.showMessageDialog(
                DIAMOND.mainFrame, message, "ERROR",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void outMessage(String s) {
        JOptionPane.showMessageDialog(
                DIAMOND.mainFrame, s, "DIAMOND",
                JOptionPane.DEFAULT_OPTION);

    }
}
