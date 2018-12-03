package diamond;

import javax.swing.JOptionPane;

import diamond.view.estimation.EstimationResultFrame;
import diamond.view.main.MainFrame;
import diamond.view.model.ModelViewFrame;
import diamond.view.model.ModelViewFrame3D;


public class DIAMOND {


    public static ModelViewFrame modelFrame;

    public static ModelViewFrame3D modelFrame3D;
    public static int tmpInt;
    public static EstimationResultFrame renderFrame;


    private static void buildModelFrame() {
        modelFrame = new ModelViewFrame();
        modelFrame.setSize(Config.INITIAL_MODEL_FRAME_WIDTH,
                Config.INITIAL_MODEL_FRAME_HEIGHT);
        modelFrame.setLocationRelativeTo(null);
        modelFrame.setVisible(false);
    }

    private static void buildRenderFrame() {
        renderFrame = new EstimationResultFrame();
        renderFrame.setVisible(false);
    }

    public static void main(String[] args) {
        // Construction of the main frame
        MainFrame.getInstance().initialize();
        // Expected folded origami frame (x-ray)
        buildModelFrame();
        // "Folded origami" frame. Estimation of the folded form.
        buildRenderFrame();
        // "Check Window". Check inputed data.
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
