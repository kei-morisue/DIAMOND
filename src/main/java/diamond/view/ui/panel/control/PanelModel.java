/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.panel.main.PanelAdd;
import diamond.view.ui.panel.main.PanelStep;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public class PanelModel extends JPanel {
    private ScreenModel screen;

    public PanelModel(Context context) {
        screen = new ScreenModel(context);

        setLayout(new BorderLayout());
        add(new PanelStep(context), BorderLayout.NORTH);
        add(screen, BorderLayout.CENTER);
        add(new PanelAdd(context), BorderLayout.SOUTH);
    }

}
