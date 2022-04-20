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
import diamond.controller.action.ExportImageAction;
import diamond.controller.action.ExportSvgAction;
import diamond.view.resource.string.Labels;
import diamond.view.ui.screen.AbstractPreviewScreen;

/**
 * @author Kei Morisue
 *
 */
public class MenuDiagramFile extends JMenu {

    private Context context;
    private AbstractPreviewScreen screen;

    public MenuDiagramFile(
            Context context,
            AbstractPreviewScreen screen) {
        super(Labels.get("preview_menu_file"));
        this.context = context;
        this.screen = screen;
        add(buildSaveImage());
        add(buildSaveSvg());
    }

    private JMenuItem buildSaveImage() {
        JMenuItem item = new JMenuItem(Labels.get("preview_menu_save_image"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));
        item.addActionListener(new ExportImageAction(this, screen));
        return item;
    }

    private JMenuItem buildSaveSvg() {
        JMenuItem item = new JMenuItem(Labels.get("preview_menu_save_svg"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
                ActionEvent.CTRL_MASK));

        item.addActionListener(
                new ExportSvgAction(
                        this,
                        screen,
                        context.getPalette()));
        return item;
    }

}
