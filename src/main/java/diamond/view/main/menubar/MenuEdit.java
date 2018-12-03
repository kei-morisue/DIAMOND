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
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.paint.util.DeleteSelectedLines;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class MenuEdit extends JMenu {

    private static ResourceHolder res = ResourceHolder.getInstance();
    private JMenuItem menuItemUndo = new JMenuItem(
            res.getString(ResourceKey.LABEL, StringID.Main.UNDO_ID));

    private JMenuItem menuItemRepeatCopy = new JMenuItem("Array Copy");
    private JMenuItem menuItemCircleCopy = new JMenuItem("Circle Copy");
    private JMenuItem menuItemUnSelectAll = new JMenuItem("UnSelect All");
    private JMenuItem menuItemDeleteSelectedLines = new JMenuItem(
            "Delete Selected Lines");


    private JMenuItem menuItemChangeOutline;
    private JMenuItem menuItemSelectAll;
    private JMenuItem menuItemCopyAndPaste;
    private JMenuItem menuItemCutAndPaste;

    public MenuEdit(MainFrame mainFrame) {
        super(res.getString(ResourceKey.LABEL, StringID.Main.EDIT_ID));

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
        addActionListeners(mainFrame);
        addAccelerators();
        addItems();
    }

    private void addActionListeners(MainFrame mainFrame) {
        menuItemUndo.addActionListener(mainFrame);
        menuItemChangeOutline.addActionListener(mainFrame);
        menuItemRepeatCopy.addActionListener(mainFrame);
        menuItemCircleCopy.addActionListener(mainFrame);
        menuItemUnSelectAll
                .addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        CreasePattern creasePattern = DocHolder.getInstance()
                                .getDoc()
                                .getCreasePattern();
                        Painter painter = new Painter();
                        painter.resetSelectedOriLines(creasePattern);
                        PaintContext.getInstance().clear(false);
                        mainFrame.getMainScreen().repaint();
                    }
                });
    }

    private void addAccelerators() {
        menuItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                ActionEvent.CTRL_MASK));
        menuItemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                ActionEvent.CTRL_MASK));
        menuItemUnSelectAll.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_ESCAPE, 0));
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
        add(menuItemRepeatCopy);
        add(menuItemCircleCopy);
        add(menuItemSelectAll);
        add(menuItemUnSelectAll);
        add(menuItemDeleteSelectedLines);
        add(menuItemUndo);
        add(menuItemChangeOutline);
    }

    public JMenuItem getMenuItemUndo() {
        return this.menuItemUndo;
    }

    public JMenuItem getMenuItemRepeatCopy() {
        return this.menuItemRepeatCopy;
    }

    public JMenuItem getMenuItemCircleCopy() {
        return this.menuItemCircleCopy;
    }

    public JMenuItem getMenuItemUnSelectAll() {
        return this.menuItemUnSelectAll;
    }

    public JMenuItem getMenuItemDeleteSelectedLines() {
        return this.menuItemDeleteSelectedLines;
    }

    public JMenuItem getMenuItemChangeOutline() {
        return this.menuItemChangeOutline;
    }

    public JMenuItem getMenuItemSelectAll() {
        return this.menuItemSelectAll;
    }

    public JMenuItem getMenuItemCopyAndPaste() {
        return this.menuItemCopyAndPaste;
    }

    public JMenuItem getMenuItemCutAndPaste() {
        return this.menuItemCutAndPaste;
    }

}
