/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
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
import diamond.view.ui.frame.TreeFrame;

/**
 * @author Kei Morisue
 *
 */
public class MainEdit extends JMenu {
    private Context context;

    public MainEdit(Context context) {
        super(Labels.get("main_menu_edit"));
        this.context = context;
        add(buildMeta(context));
    }

    private JMenuItem buildMeta(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_edit_tree"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                ActionEvent.CTRL_MASK));
        item.addActionListener(new Action());
        return item;
    }

    private class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TreeFrame(context);
        }

    }
}
