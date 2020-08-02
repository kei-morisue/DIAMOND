/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.frame;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import diamond.Config;
import diamond.controller.Context;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;
import diamond.view.ui.menu.MainBar;
import diamond.view.ui.panel.control.PanelControl;
import diamond.view.ui.panel.main.PanelMain;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
    private Context context;
    private MainBar menubar;

    public MainFrame(Context context) {
        this.context = context;
        this.menubar = new MainBar(context);
        IconBuilder.set(this, "diamond.gif");
        buildComponents(getContentPane());
        setTitle(Labels.get("main_frame_title"));
        setVisible(true);
        setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(menubar);
    }

    private void buildComponents(Container panel) {
        panel.setLayout(new GridLayout(1, 2));
        panel.add(buildControl());
        panel.add(buildMain());
    }

    private JPanel buildMain() {
        return new PanelMain(context);
    }

    private JPanel buildControl() {
        return new PanelControl(context);
    }
}
