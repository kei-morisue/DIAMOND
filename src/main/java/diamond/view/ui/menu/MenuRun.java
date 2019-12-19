/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.controller.Context;
import diamond.controller.action.PreviewAction;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class MenuRun extends JMenu {
    public MenuRun(Context context) {
        super(Labels.get("main_menu_run"));
        add(buildPreview(context));
    }

    public JMenuItem buildPreview(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_run_preview"));
        item.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        item.addActionListener(new PreviewAction(context));
        return item;
    }
}
