/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.frame;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import defox.Config;
import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.panel.option.PanelFace;
import diamond.view.ui.panel.option.PanelLine;
import diamond.view.ui.panel.option.PanelPage;

/**
 * @author Kei Morisue
 *
 */
public class StyleFrame extends JFrame {
    public StyleFrame(Context context) {
        setTitle(Labels.get("main_menu_option_style"));
        setSize(Config.STYLE_FRAME_WIDTH, Config.STYLE_FRAME_HEIGHT);
        JTabbedPane pane = new JTabbedPane();
        pane.addTab(
                Labels.get("tab_style_line"),
                new PanelLine());
        pane.addTab(
                Labels.get("tab_style_face"),
                new PanelFace(context));
        pane.addTab(
                Labels.get("tab_style_page"),
                new PanelPage(context));
        add(pane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
