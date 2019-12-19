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
import diamond.view.resource.string.Labels;
import diamond.view.ui.screen.PreviewScreen;

/**
 * @author Kei Morisue
 *
 */
public class MenuDiagramFile extends JMenu {

    private Context context;
    private PreviewScreen screen;

    public MenuDiagramFile(Context context, PreviewScreen screen) {
        super(Labels.get("preview_menu_file"));
        this.context = context;
        this.screen = screen;
        add(buildSave());
    }

    private JMenuItem buildSave() {
        JMenuItem item = new JMenuItem(Labels.get("preview_menu_save"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));
        //item.addActionListener(new ImageExportAction(this, screen));
        //TODO
        return item;
    }

}
