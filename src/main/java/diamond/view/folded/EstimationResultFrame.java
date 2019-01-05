/**
 * DIAMOND - Origami Pattern Editor
 */

package diamond.view.folded;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import diamond.viewsetting.folded.RenderFrameSettingDB;

public class EstimationResultFrame extends JFrame
        implements Observer {

    private RenderFrameSettingDB setting = RenderFrameSettingDB.getInstance();

    FoldedModelScreen screen;
    EstimationResultUI ui;
    public JLabel hintLabel;

    public EstimationResultFrame() {
        setting.addObserver(this);

        setTitle("Folded Origami");
        screen = new FoldedModelScreen();
        ui = new EstimationResultUI();
        ui.setScreen(screen);
        hintLabel = new JLabel("L: Rotate / Wheel: Zoom");
        setBounds(0, 0, 800, 600);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(ui, BorderLayout.WEST);
        getContentPane().add(screen, BorderLayout.CENTER);
        getContentPane().add(hintLabel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (setting.isFrameVisible()) {
            screen.resetViewMatrix();
            screen.redrawOrigami();
            ui.updateLabel();
            setVisible(true);
        }
    }
}
