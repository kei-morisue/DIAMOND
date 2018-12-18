/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.menubar.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.doc.DocHolder;
import diamond.paint.core.PaintConfig;
import diamond.paint.core.PaintContext;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringID;

/**
 * @author long_
 *
 */
public class Undo
        extends JMenuItem {
    private static Undo instance = null;

    public static Undo getInstance() {
        if (instance == null) {
            instance = new Undo();
        }
        return instance;
    }

    public Undo() {
        super(ResourceHolder.getLabelString(
                StringID.Main.UNDO_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                ActionEvent.CTRL_MASK));
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (PaintConfig.getMouseAction() != null) {
                    PaintConfig.getMouseAction()
                            .undo(PaintContext.getInstance());
                } else {
                    DocHolder.getDoc().loadUndoInfo();
                }
                PaintContext.getPainterScreen().repaint();
            }
        });
    }

}
