/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.frame;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

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
        setTitle(Labels.get("main_frame_title"));
        IconBuilder.set(this, "diamond.gif");
        setJMenuBar(menubar);
        Container panel = getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(west, BorderLayout.CENTER);
        setVisible(true);
        setSize(Config.MAIN_FRAME_WIDTH,
                Config.MAIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
