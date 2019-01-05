/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.Config;
import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.paint.core.PaintContext;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.view.paint.PaintFrame;

/**
 * @author long_
 *
 */
public class New extends JMenuItem implements ActionListener {
    private static New instance = null;

    public static New getInstance() {
        if (instance == null) {
            instance = new New();
        }
        return instance;
    }

    private New() {
        super(ResourceHolder.getLabelString(LABEL.NEW));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintContext context = PaintContext.getInstance();
        context.dispGrid = true;
        context.notifyObservers();

        DocHolder.setDoc(new Doc(Config.DEFAULT_PAPER_SIZE));
        PaintFrame.getInstance().setTitle(DocHolder.getDoc().getDataFileName());
        PaintContext.getPainterScreen().repaint();
    }
}
