/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;
import diamond.view.ui.frame.StyleFrame;

/**
 * @author Kei Morisue
 *
 */
public class MenuOption extends JMenu {
    public MenuOption(Context context) {
        super(Labels.get("main_menu_option"));
        JMenuItem item = new JMenuItem(
                Labels.get("main_menu_option_style"));
        add(item);
        item.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StyleFrame(context);
            }
        });
    }
}
