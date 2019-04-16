/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.controller.file.action.ExportAction;
import diamond.controller.file.action.LoadAction;
import diamond.controller.paint.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.draw.style.StyleFrame;

/**
 * @author long_
 *
 */
public class MenuBar extends JMenuBar {

    public MenuBar(PaintContext paintContext) {
        add(buildFileMenu(paintContext));
        add(buildRunMenu(paintContext));
        add(buildOptionMenu(paintContext));
    }

    /**
     *
     */
    private JMenu buildRunMenu(PaintContext paintContext) {
        return new BuildPagesMenu(paintContext);
    }

    private JMenu buildOptionMenu(PaintContext paintContext) {
        JMenu menu = new JMenu(ResourceHolder.getLabelString(LABEL.OPTION));
        JMenuItem style = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.STYLE));
        menu.add(style);
        style.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StyleFrame();
            }
        });
        return menu;
    }

    private JMenu buildFileMenu(PaintContext paintContext) {
        JMenu menu = new JMenu(ResourceHolder.getLabelString(LABEL.FILE));
        JMenuItem save = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.SAVE));
        JMenuItem open = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.OPEN));
        menu.add(open);
        menu.add(save);
        save.addActionListener(new ExportAction(paintContext, save));
        open.addActionListener(new LoadAction(paintContext, open));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        return menu;
    }
}
