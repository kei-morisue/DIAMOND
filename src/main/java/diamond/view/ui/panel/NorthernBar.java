/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import diamond.controller.paint.context.AbstractScreenContext;
import diamond.view.screen.ModelScreen;
import diamond.view.screen.PaintScreen;
import diamond.view.ui.button.ModelSwitchButton;

/**
 * @author long_
 *
 */
public class NorthernBar extends JPanel {
    public NorthernBar(PaintScreen paintScreen,
            ModelScreen modelScreen) {
        setLayout(new FlowLayout());
        add(new ModelSwitchButton(ModelSwitchButton.PREV,
                paintScreen, modelScreen));
        add(new ModelSwitchButton(ModelSwitchButton.NEXT,
                paintScreen, modelScreen));
        AbstractScreenContext context = paintScreen.getContext();
        add(new StepNoComboBox(
                context.getPalette()));

    }
}
