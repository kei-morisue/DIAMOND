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
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.MainFrame;
import diamond.viewsetting.main.MainFrameSettingDB;

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
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.OPEN_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mainFrame = MainFrame.getInstance();
        MainFrameSettingDB.getInstance().notifyObservers();

        FileIOUtil.openFile(mainFrame, null);
        PaintContext.getPainterScreen().repaint();
        mainFrame.setTitle(DocHolder.getDoc().getDataFileName());

    }
}
