/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import diamond.Initials;
import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.PreviewScreen;
import diamond.view.ui.button.PageSwitchButton;
import diamond.view.ui.menu.MenuDiagramFile;

/**
 * @author long_
 *
 */
public class PreviewFrame extends JFrame {
    private PaintContext paintContext;
    private PreviewScreen screen;

    public PreviewFrame(PaintContext paintContext) {
        this.paintContext = paintContext;
        this.screen = new PreviewScreen(this.paintContext);
        setVisible(true);
        setTitle(ResourceHolder.getLabelString(LABEL.PREVIEW));
        buildContents(getContentPane());
        buildMenu();
    }

    private void buildMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new MenuDiagramFile(paintContext, screen));
        setJMenuBar(menuBar);
    }

    private void buildContents(Container panel) {
        panel.setLayout(new BorderLayout());
        panel.add(screen, BorderLayout.CENTER);
        panel.add(new PageSwitchButton(
                screen,
                PageSwitchButton.NEXT),
                BorderLayout.EAST);
        panel.add(new PageSwitchButton(
                screen,
                PageSwitchButton.PREV),
                BorderLayout.WEST);
        setSize(new Dimension(Initials.FRAME_WIDTH, Initials.FRAME_HEIGHT));
        setLocationRelativeTo(null);
    }
}
