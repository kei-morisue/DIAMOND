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
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.math.field.F;
import diamond.view.resource.IconBuilder;
import diamond.view.resource.string.Labels;
import diamond.view.ui.menu.MainBar;
import diamond.view.ui.panel.control.PanelModel;
import diamond.view.ui.panel.main.PanelCp;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
    private Diagram diagram;
    private Context context;
    private MainBar menubar;

    public MainFrame(F one) {
        diagram = new Diagram(one.mul(Config.SIZE));
        context = new Context(diagram);
        menubar = new MainBar(context);
        buildComponents(getContentPane());
        setVisual();
    }

    private void setVisual() {
        IconBuilder.set(this, "logo.gif");
        setTitle(Labels.get("main_frame_title"));
        setVisible(true);
        setSize(Config.MAIN_FRAME_WIDTH, Config.MAIN_FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void buildComponents(Container panel) {
        panel.setLayout(new GridLayout(1, 2));
        panel.add(buildModel());
        panel.add(buildCp());
        setJMenuBar(menubar);
    }

    private JPanel buildCp() {
        return new PanelCp(context);
    }

    private JPanel buildModel() {
        return new PanelModel(context);
    }
}
