/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.paint.creasepattern.CreasePattern;
import diamond.paint.creasepattern.Painter;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class UnselectAll
        extends JMenuItem implements ActionListener {
    private static UnselectAll instance = null;

    public static UnselectAll getInstance() {
        if (instance == null) {
            instance = new UnselectAll();
        }
        return instance;
    }

    public UnselectAll() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.UNSELECT_ALL_ID));
        setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_ESCAPE, 0));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CreasePattern creasePattern = DocHolder.getInstance()
                .getDoc()
                .getCreasePattern();
        Painter painter = new Painter();
        painter.resetSelectedOriLines(creasePattern);
        PaintContext.getInstance().clear(false);
        MainFrame.getInstance().getCpScreen().repaint();
    }

}
