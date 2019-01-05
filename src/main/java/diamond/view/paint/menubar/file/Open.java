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

import diamond.doc.DocHolder;
import diamond.file.FileIOUtil;
import diamond.paint.core.PaintContext;
import diamond.resource.ResourceHolder;
import diamond.resource.string.StringKey.LABEL;
import diamond.view.paint.PaintFrame;
import diamond.viewsetting.paint.MainFrameSettingDB;

/**
 * @author long_
 *
 */
public class Open extends JMenuItem implements ActionListener {
    private static Open instance = null;

    public static Open getInstance() {
        if (instance == null) {
            instance = new Open();
        }
        return instance;
    }

    private Open() {
        super(ResourceHolder.getLabelString(LABEL.OPEN));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PaintFrame mainFrame = PaintFrame.getInstance();
        MainFrameSettingDB.getInstance().notifyObservers();

        FileIOUtil.openFile(mainFrame, null);
        PaintContext.getPainterScreen().repaint();
        mainFrame.setTitle(DocHolder.getDoc().getDataFileName());

    }
}
