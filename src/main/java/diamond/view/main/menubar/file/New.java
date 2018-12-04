/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.menubar.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.doc.Doc;
import diamond.doc.DocHolder;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.estimation.EstimationResultFrame;
import diamond.view.main.MainFrame;
import diamond.view.model.ModelViewFrame;
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

    public New() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
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

        DocHolder.getInstance().setDoc(new Doc());
        ModelViewFrame.getInstance().repaint();
        ModelViewFrame.getInstance().setVisible(false);
        EstimationResultFrame.getInstance().setVisible(false);
        MainFrame.getInstance().updateTitleText();
        MainFrame.getInstance().getCpScreen().repaint();
    }
}
