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

import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;

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

    public Open() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.OPEN_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.openFile(null);
        mainFrame.getMainScreen().repaint();
        mainFrame.updateTitleText();

    }
}
