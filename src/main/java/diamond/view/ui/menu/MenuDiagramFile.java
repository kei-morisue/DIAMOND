/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.controller.file.action.ImageExportAction;
import diamond.controller.paint.context.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.screen.PreviewScreen;

/**
 * @author long_
 *
 */
public class MenuDiagramFile extends JMenu {

    PaintContext paintContext;
    PreviewScreen screen;

    public MenuDiagramFile(PaintContext paintContext, PreviewScreen screen) {
        super(ResourceHolder.getLabelString(LABEL.FILE));
        this.paintContext = paintContext;
        this.screen = screen;
        add(buildSave());
    }

    private JMenuItem buildSave() {
        JMenuItem item = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.SAVE));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));
        item.addActionListener(new ImageExportAction(this, screen));
        return item;
    }

}
