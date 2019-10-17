/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import diamond.controller.paint.context.AbstractScreenContext;
import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;
import diamond.view.ui.button.DiagramDestroyButton;
import diamond.view.ui.button.DiagramInsertButton;
import diamond.view.ui.button.DiagramSwitchButton;

/**
 * @author long_
 *
 */
public class CenterPanel extends JPanel {
    public CenterPanel(PaintScreen paintScreen, ModelScreen modelScreen) {
        setLayout(new BorderLayout());
        JPanel screens = buildScreens(paintScreen, modelScreen);
        add(screens, BorderLayout.CENTER);
        add(
                new DiagramSwitchButton(DiagramSwitchButton.NEXT,
                        paintScreen, modelScreen),
                BorderLayout.EAST);
        add(
                new DiagramSwitchButton(DiagramSwitchButton.PREV,
                        paintScreen, modelScreen),
                BorderLayout.WEST);
        AbstractScreenContext context = paintScreen.getContext();
        add(
                new DiagramDestroyButton(context),
                BorderLayout.SOUTH);
        add(
                new DiagramInsertButton(context),
                BorderLayout.NORTH);
    }

    private JPanel buildScreens(PaintScreen paintScreen,
            ModelScreen modelScreen) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(paintScreen);
        panel.add(modelScreen);
        return panel;
    }
}
