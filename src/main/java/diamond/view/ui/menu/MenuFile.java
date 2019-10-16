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

import diamond.controller.file.action.ExportAction;
import diamond.controller.file.action.LoadAction;
import diamond.controller.paint.context.ModelContext;
import diamond.controller.paint.context.PaintContext;
import diamond.view.resource.ResourceHolder;
import diamond.view.resource.string.StringKey.LABEL;

/**
 * @author long_
 *
 */
public class MenuFile extends JMenu {
    public MenuFile(PaintContext paintContext, ModelContext modelContext) {
        super(ResourceHolder.getLabelString(LABEL.FILE));
        add(buildOpen(paintContext, modelContext));
        add(buildSave(paintContext));
    }

    private JMenuItem buildOpen(PaintContext paintContext,
            ModelContext modelContext) {
        JMenuItem item = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.OPEN));
        item.addActionListener(
                new LoadAction(paintContext, modelContext, item));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        return item;
    }

    private JMenuItem buildSave(PaintContext paintContext) {
        JMenuItem item = new JMenuItem(
                ResourceHolder.getLabelString(LABEL.SAVE));
        item.addActionListener(new ExportAction(paintContext, item));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        return item;
    }
}
