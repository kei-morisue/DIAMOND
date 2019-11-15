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
    private JPanel east = new East();
    private JPanel west = new West();
    private JMenuBar menuBar = new MenuBar();

    public MainFrame() {
        setTitle(Labels.get("title"));
        setIconImage(IconBuilder.load("diamond.gif"));
        setJMenuBar(menuBar);
        Container panel = getContentPane();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(east);
        panel.add(west);
        setVisible(true);
        setSize(Config.MAIN_FRAME_WIDTH,
                Config.MAIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
