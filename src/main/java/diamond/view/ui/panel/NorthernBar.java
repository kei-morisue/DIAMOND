/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;
import diamond.view.ui.button.ModelJumpButton;
import diamond.view.ui.button.ModelSwitchButton;

/**
 * @author long_
 *
 */
public class NorthernBar extends JPanel {
    public NorthernBar(PaintScreen paintScreen,
            ModelScreen modelScreen) {
        setLayout(new BorderLayout());
        add(new ModelJumpButton(ModelJumpButton.TOP,
                paintScreen, modelScreen), BorderLayout.EAST);
        add(new ModelJumpButton(ModelJumpButton.BOTTOM,
                paintScreen, modelScreen), BorderLayout.WEST);

        add(builCenterPanel(paintScreen, modelScreen), BorderLayout.CENTER);

    }

    private JPanel builCenterPanel(PaintScreen paintScreen,
            ModelScreen modelScreen) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new ModelSwitchButton(ModelSwitchButton.PREV,
                paintScreen, modelScreen));
        panel.add(new ModelSwitchButton(ModelSwitchButton.NEXT,
                paintScreen, modelScreen));
        return panel;
    }
}
