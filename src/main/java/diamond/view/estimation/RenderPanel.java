/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.estimation;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author long_
 *
 */
public class RenderPanel extends JPanel {

    public RenderPanel() {
        FoldedModelScreen screen = new FoldedModelScreen();
        EstimationResultUI ui = new EstimationResultUI(screen);
        setLayout(new BorderLayout());
        add(ui, BorderLayout.WEST);
        add(screen, BorderLayout.CENTER);
        add(new HintLabel(), BorderLayout.SOUTH);
    }
}
