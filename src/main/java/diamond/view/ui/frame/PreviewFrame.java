/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import diamond.Config;
import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.button.PageSwitch;
import diamond.view.ui.menu.MenuDiagramFile;
import diamond.view.ui.screen.PreviewScreen;

/**
 * @author Kei Morisue
 *
 */
public class PreviewFrame extends JFrame {
    private Context context;
    private PreviewScreen screen;

    public PreviewFrame(Context context) {
        this.context = context;
        this.screen = new PreviewScreen(context);
        setVisible(true);
        setTitle(Labels.get("preview_frame_title"));
        buildContents(getContentPane());
        buildMenu();
    }

    private void buildMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new MenuDiagramFile(context, screen));
        setJMenuBar(menuBar);
    }

    private void buildContents(Container panel) {
        panel.setLayout(new BorderLayout());
        panel.add(screen, BorderLayout.CENTER);
        panel.add(new PageSwitch(
                screen,
                PageSwitch.NEXT),
                BorderLayout.EAST);
        panel.add(new PageSwitch(
                screen,
                PageSwitch.PREV),
                BorderLayout.WEST);
        setSize(new Dimension(Config.PREVIEW_FRAME_WIDTH,
                Config.PREVIEW_FRAME_HEIGHT));
        setLocationRelativeTo(null);
    }
}
