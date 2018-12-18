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
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.MainFrame;
import diamond.viewsetting.main.MainScreenSettingDB;

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
        super(ResourceHolder.getString(ResourceKey.LABEL,
                StringID.Main.NEW_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainScreenSettingDB screenSetting = MainScreenSettingDB.getInstance();
        screenSetting.setGridVisible(true);
        screenSetting.notifyObservers();

        DocHolder.setDoc(new Doc(Config.DEFAULT_PAPER_SIZE));
        MainFrame.getInstance().setTitle(DocHolder.getDoc().getDataFileName());
        PaintContext.getPainterScreen().repaint();
    }
}
