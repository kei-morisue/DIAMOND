/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.frame;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import diamond.Config;
import diamond.controller.Context;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;
import diamond.view.ui.menu.MenuBar;
import diamond.view.ui.panel.East;
import diamond.view.ui.panel.West;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
    private Context context;
    private JPanel east;
    private JPanel west;
    private JMenuBar menuBar;

    public MainFrame() {
        this.context = new Context();
        buildFrame();
    }

    public MainFrame(int i) {
        this.context = new Context();
        buildFrame();
    }

    private void buildFrame() {
        this.east = new East(context);
        this.west = new West(context);
        this.menuBar = new MenuBar(context);
        setTitle(Labels.get("main_frame_title"));
        IconBuilder.set(this, "diamond.gif");
        setJMenuBar(menuBar);
        Container panel = getContentPane();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(west);
        panel.add(east);
        setVisible(true);
        setSize(Config.MAIN_FRAME_WIDTH,
                Config.MAIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
