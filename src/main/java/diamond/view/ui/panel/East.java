/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.button.CpSwitch;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class East extends JPanel {
    private JPanel north = new JPanel();
    private JPanel south = new JPanel();
    private JPanel center;

    public East(Context context) {
        this.center = new PaintScreen(context);
        setLayout(new BorderLayout());
        buildNorth(context);

        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);
    }

    private void buildNorth(Context context) {
        north.setLayout(new BorderLayout());
        north.add(new CpSwitch(CpSwitch.BOTTOM, context),
                BorderLayout.WEST);
        north.add(new CpSwitch(CpSwitch.TOP, context),
                BorderLayout.EAST);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new CpSwitch(CpSwitch.PREV, context));
        panel.add(new CpSwitch(CpSwitch.NEXT, context));
        north.add(panel, BorderLayout.CENTER);
    }

}
