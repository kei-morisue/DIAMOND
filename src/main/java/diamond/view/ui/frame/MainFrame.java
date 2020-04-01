/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import diamond.Config;
import diamond.controller.Context;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;
import diamond.view.ui.menu.Bar;
import diamond.view.ui.screen.MainScreen;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
    private Context context;
    private Bar menubar;
    private MainScreen west;

    public MainFrame(Context context) {
        this.context = context;
        this.menubar = new Bar(context);
        this.west = new MainScreen(context);
        IconBuilder.set(this, "diamond.gif");
        buildConponents(getContentPane());
        setTitle(Labels.get("main_frame_title"));
        setVisible(true);
        setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menubar);
    }

    private void buildConponents(Container panel) {
        panel.setLayout(new GridLayout(1, 2));
        panel.add(buildWest());
        panel.add(buildCenter());
    }

    private JPanel buildCenter() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));
        jPanel.setBackground(Color.BLUE);
        //jPanel.add(west);
        return jPanel;
    }

    //TODO
    private JPanel buildWest() {
        JPanel comp = new JPanel();
        comp.setBackground(Color.RED);
        comp.add(new JButton("foo"));
        return comp;
    }
}
