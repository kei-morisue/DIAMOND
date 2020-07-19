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
import diamond.controller.Palette;
import diamond.controller.action.ExportDmdAction;
import diamond.controller.action.LoadAction;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class MenuFile extends JMenu {
    public MenuFile(Context context) {
        super(Labels.get("main_menu_file"));
        add(buildNew(context));
        add(buildOpen(context));
        add(buildSave(context));
    }

    private JMenuItem buildNew(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_file_new"));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                context.initialize();
                context.setPalette(new Palette());
                context.setCurrentStep(0);
                context.repaint();
            }
        });
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        return item;
    }

    private JMenuItem buildOpen(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_file_open"));
        item.addActionListener(new LoadAction(context, this));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        return item;
    }

    private JMenuItem buildSave(Context context) {
        JMenuItem item = new JMenuItem(Labels.get("main_menu_file_save"));
        item.addActionListener(new ExportDmdAction(context, this));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        return item;
    }
}
