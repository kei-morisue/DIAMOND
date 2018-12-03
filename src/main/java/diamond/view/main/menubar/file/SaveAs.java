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

import diamond.file.FileHistory;
import diamond.resource.ResourceHolder;
import diamond.resource.ResourceKey;
import diamond.resource.StringID;
import diamond.view.main.MainFrame;

/**
 * @author long_
 *
 */
public class SaveAs extends JMenuItem implements ActionListener {
    private static SaveAs instance = null;

    public static SaveAs getInstance() {
        if (instance == null) {
            instance = new SaveAs();
        }
        return instance;
    }

    public SaveAs() {
        super(ResourceHolder.getInstance().getString(ResourceKey.LABEL,
                StringID.Main.SAVE_AS_ID));
        setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.CTRL_MASK));
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.saveAs(FileHistory.getLastDirectory());
    }
}
