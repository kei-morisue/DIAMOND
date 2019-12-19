/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.ui.button.CpDestroy;
import diamond.view.ui.button.CpInsert;
import diamond.view.ui.button.CpJump;
import diamond.view.ui.button.CpSwitch;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.OffsetSymbolScreen;
import diamond.view.ui.screen.OffsetVertexScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class East extends JPanel {
    private JPanel north = new JPanel();
    private JPanel south = new JPanel();
    private JPanel paintPanel = new JPanel();
    private PaintScreen paintScreen;
    private OffsetVertexScreen offsetVertexScreen;
    private OffsetSymbolScreen offsetSymbolScreen;

    private CardLayout layout = new CardLayout();

    public East(Context context) {
        context.setEast(this);
        setLayout(new BorderLayout());
        buildNorth(context);
        buildSouth(context);
        buildCenter(context);

        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        add(paintPanel, BorderLayout.CENTER);
    }

    private void buildCenter(Context context) {
        paintScreen = new PaintScreen(context);
        offsetVertexScreen = new OffsetVertexScreen(context);
        offsetSymbolScreen = new OffsetSymbolScreen(context);

        paintPanel.setLayout(layout);
        paintPanel.add(offsetSymbolScreen, "offset_s");
        paintPanel.add(offsetVertexScreen, "offset_v");
        paintPanel.add(paintScreen, "paint");
        layout.show(paintPanel, "paint");
    }

    public void setPaintScreen(String screenName) {
        layout.show(paintPanel, screenName);
    }

    public AbstractScreen getPaintScreen() {
        for (Component c : paintPanel.getComponents()) {
            if (!c.isVisible()) {
                continue;
            }
            if (c == paintScreen) {
                paintScreen.reset();
                return paintScreen;
            }
            if (c == offsetVertexScreen) {
                paintScreen.reset();
                return offsetVertexScreen;
            }
        }
        return paintScreen;
    }

    private void buildNorth(Context context) {
        north.setLayout(new BorderLayout());
        north.add(new CpJump(CpJump.BOTTOM, context),
                BorderLayout.WEST);
        north.add(new CpJump(CpJump.TOP, context),
                BorderLayout.EAST);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new CpSwitch(CpSwitch.PREV, context));
        panel.add(new CpSwitch(CpSwitch.NEXT, context));
        north.add(panel, BorderLayout.CENTER);
    }

    private void buildSouth(Context context) {
        south.setLayout(new BorderLayout());
        south.add(new CpInsert(context), BorderLayout.EAST);
        south.add(new CpDestroy(context), BorderLayout.WEST);
    }
}
