/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.ui.menu;

import java.awt.event.ActionEvent;
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

/**
 * @author long_
 *
 */
public class MenuBar extends JMenuBar {

    public MenuBar(PaintContext paintContext) {
        JMenu fileMenu = new JMenu(ResourceHolder.getLabelString(LABEL.FILE));
        JMenuItem save = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.SAVE));
        JMenuItem open = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.OPEN));
        fileMenu.add(open);
        fileMenu.add(save);
        add(fileMenu);
        save.addActionListener(new ExportAction(paintContext, save));
        open.addActionListener(new LoadAction(paintContext, open));

        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
    }
}
