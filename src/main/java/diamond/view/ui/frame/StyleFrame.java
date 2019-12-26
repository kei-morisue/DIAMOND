/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.frame;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import diamond.Config;
import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.panel.OptionFace;
import diamond.view.ui.panel.OptionLine;
import diamond.view.ui.panel.OptionPage;

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
                new OptionLine(context));
        pane.addTab(
                Labels.get("tab_style_face"),
                new OptionFace(context));
        pane.addTab(
                Labels.get("tab_style_page"),
                new OptionPage(context));
        add(pane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
