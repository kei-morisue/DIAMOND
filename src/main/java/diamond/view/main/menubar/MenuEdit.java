/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.bind.ButtonFactory;
import diamond.bind.PaintActionButtonFactory;
import diamond.paint.util.DeleteSelectedLines;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;
import diamond.view.main.menubar.edit.CircleCopy;
import diamond.view.main.menubar.edit.RepeatCopy;
import diamond.view.main.menubar.edit.Undo;
import diamond.view.main.menubar.edit.UnselectAll;

/**
 * @author long_
 *
 */
public class MenuEdit extends JMenu {

    private static MenuEdit instance = null;

    private static MainFrame mainFrame = MainFrame.getInstance();

    public static JMenuItem menuItemChangeOutline;

    public static JMenuItem menuItemCopyAndPaste;
    public static JMenuItem menuItemCutAndPaste;
    public static JMenuItem menuItemDeleteSelectedLines = new JMenuItem(
            ResourceHolder.getString(ResourceKey.LABEL,
                    StringID.Main.DELETE_SELECTED_LINES_ID));

    public static JMenuItem menuItemSelectAll;

    public static MenuEdit getInstance() {
        if (instance == null) {
            instance = new MenuEdit();
        }
        return instance;
    }

    public MenuEdit() {
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.EDIT_ID));

        ButtonFactory buttonFactory = new PaintActionButtonFactory();
        menuItemChangeOutline = (JMenuItem) buttonFactory.create(mainFrame,
                JMenuItem.class, ResourceKey.LABEL, StringID.EDIT_CONTOUR_ID);
        menuItemSelectAll = (JMenuItem) buttonFactory.create(mainFrame,
                JMenuItem.class, ResourceKey.LABEL,
                StringID.SELECT_ALL_LINE_ID);
        menuItemCopyAndPaste = (JMenuItem) buttonFactory.create(mainFrame,
                JMenuItem.class, ResourceKey.LABEL, StringID.COPY_PASTE_ID);
        menuItemCutAndPaste = (JMenuItem) buttonFactory.create(
                mainFrame, JMenuItem.class, StringID.CUT_PASTE_ID);
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
        add(menuItemChangeOutline);
    }

}
