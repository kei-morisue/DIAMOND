/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.bind.button.editline.CopyPasteButtonFactory;
import diamond.bind.button.editline.CutPasteButtonFactory;
import diamond.bind.button.editline.EditContourButtonFactory;
import diamond.bind.button.editline.SelectAllLineButtonFactory;
import diamond.paint.util.DeleteSelectedLines;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;
import diamond.view.paint.menubar.edit.CircleCopy;
import diamond.view.paint.menubar.edit.RepeatCopy;
import diamond.view.paint.menubar.edit.Undo;
import diamond.view.paint.menubar.edit.UnselectAll;

/**
 * @author long_
 *
 */
public class MenuEdit extends JMenu {
    private static MenuEdit instance = null;

    public static MenuEdit getInstance() {
        if (instance == null) {
            instance = new MenuEdit();
        }
        return instance;
    }

    private static JMenuItem menuItemEditCountour;

    private static JMenuItem menuItemCopyAndPaste;
    private static JMenuItem menuItemCutAndPaste;
    private static JMenuItem menuItemDeleteSelectedLines = new JMenuItem(
            ResourceHolder
                    .getLabelString(StringID.Main.DELETE_SELECTED_LINES_ID));

    private static JMenuItem menuItemSelectAll;

    private MenuEdit() {
        super(ResourceHolder.getLabelString(StringID.Main.EDIT_ID));

        menuItemEditCountour = new EditContourButtonFactory()
                .create(JMenuItem.class);
        menuItemSelectAll = new SelectAllLineButtonFactory()
                .create(JMenuItem.class);
        menuItemCopyAndPaste = new CopyPasteButtonFactory()
                .create(JMenuItem.class);
        menuItemCutAndPaste = new CutPasteButtonFactory()
                .create(JMenuItem.class);
        addAccelerators();
        addItems();
    }

    private void addAccelerators() {
        menuItemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));
        menuItemDeleteSelectedLines
                .addActionListener(new DeleteSelectedLines());
        menuItemDeleteSelectedLines.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, 0));
        menuItemCopyAndPaste.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menuItemCutAndPaste.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    }

    private void addItems() {
        add(menuItemCopyAndPaste);
        add(menuItemCutAndPaste);
        add(RepeatCopy.getInstance());
        add(CircleCopy.getInstance());
        add(menuItemSelectAll);
        add(UnselectAll.getInstance());
        add(menuItemDeleteSelectedLines);
        add(Undo.getInstance());
        add(menuItemEditCountour);
    }

}
