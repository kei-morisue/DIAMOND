/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.PreviewScreen;
import diamond.view.screen.draw.style.PageStyle;
import diamond.view.ui.button.PageSwitchButton;

/**
 * @author long_
 *
 */
public class PreviewFrame extends JFrame {
    PaintContext paintContext;

    public PreviewFrame(PaintContext paintContext) {
        this.paintContext = paintContext;
        setVisible(true);
        setTitle(ResourceHolder.getLabelString(LABEL.PREVIEW));
        build(getContentPane());
    }

    /**
     *
     */
    private void build(Container panel) {
        panel.setLayout(new BorderLayout());
        PreviewScreen screen = new PreviewScreen(paintContext);
        panel.add(screen, BorderLayout.CENTER);
        panel.add(new PageSwitchButton(
                screen,
                PageSwitchButton.NEXT),
                BorderLayout.EAST);
        panel.add(new PageSwitchButton(
                screen,
                PageSwitchButton.PREV),
                BorderLayout.WEST);
        setSize(new Dimension(PageStyle.FRAME_WIDTH, PageStyle.FRAME_HEIGHT));
        setLocationRelativeTo(null);
    }
}
